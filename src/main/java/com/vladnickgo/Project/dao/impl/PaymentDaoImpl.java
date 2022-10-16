package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.PaymentDao;
import com.vladnickgo.Project.dao.entity.Payment;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

public class PaymentDaoImpl extends AbstractCrudDaoImpl<Payment> implements PaymentDao {
    private static final Logger LOGGER = Logger.getLogger(PaymentDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO payments (order_id, payment_date, amount) " +
            "VALUES (?, ?, ?);";

    private static final String FIND_BY_ID = "SELECT * FROM payments WHERE payment_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM payments;";

    private static final String UPDATE = "UPDATE payments " +
            "SET order_id=?, payment_date=?, amount=? " +
            "WHERE payment_id = ?; ";

    private static final String CREATE_ORDER_BY_PARAMETERS = "INSERT INTO " +
            "orders(user_id, cabin_status_id, order_date, order_status_id, cruise_id) " +
            "VALUES (?, ?, ?, ?, ?);";

    private static final String CREATE_PAYMENT_BY_ORDER_ID = "INSERT INTO payments(order_id, payment_status_id, payment_date, amount) " +
            "VALUES ((SELECT order_id FROM orders WHERE cabin_status_id=? AND order_status_id=?),?,?,?);";

    private static final String FIND_PAYMENT_BY_STATUS_AND_CABIN_STATUS = "SELECT * FROM payments " +
            "LEFT JOIN orders o on payments.order_id = o.order_id " +
            "LEFT JOIN users u on u.user_id = o.user_id " +
            "LEFT JOIN cabin_statuses cs on cs.cabin_status_id = o.cabin_status_id " +
            "LEFT JOIN cabins ca on ca.cabin_id = cs.cabin_id " +
            "LEFT JOIN cabin_types ct on ct.cabin_type_id = ca.cabin_type_id " +
            "LEFT JOIN cabin_status_statements css on css.status_statement_id = cs.status_statement_id " +
            "LEFT JOIN ships s on s.ship_id = ca.ship_id " +
            "LEFT JOIN order_statuses os on os.order_status_id = o.order_status_id " +
            "LEFT JOIN cruises c on c.cruise_id = o.cruise_id " +
            "LEFT JOIN routes r on r.route_id = c.route_id " +
            "LEFT JOIN cruise_statuses cs2 on cs2.cruise_status_id = c.cruise_status_id " +
            "WHERE o.cabin_status_id = ? AND payment_status_id=?; ";

    private static final String UPDATE_CABIN_STATUS_BY_ID = "UPDATE cabin_statuses " +
            "SET status_statement_id=? WHERE cabin_status_id=? ";

    private static final Integer ORDER_STATUS_IN_PROGRESS = 1;
    private static final Integer PAYMENT_STATUS_PAID = 1;
    private static final Integer CABIN_STATUS_STATEMENT_BUSY = 3;

    private static final LocalDate DATE_NOW = LocalDate.now();

    public PaymentDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected Payment mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToPayment(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Payment entity) throws SQLException {
        preparedStatement.setInt(1, entity.getOrder().getId());
        preparedStatement.setDate(2, Date.valueOf(DATE_NOW));
        preparedStatement.setInt(3, entity.getAmount());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Payment entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(4, entity.getId());
    }

    @Override
    public Payment addPayment(Payment payment) {
        try (Connection connection = connector.getConnection();
             PreparedStatement createOrder = connection.prepareStatement(CREATE_ORDER_BY_PARAMETERS);
             PreparedStatement createPayment = connection.prepareStatement(CREATE_PAYMENT_BY_ORDER_ID);
             PreparedStatement updateCabinStatus = connection.prepareStatement(UPDATE_CABIN_STATUS_BY_ID)) {
            PreparedStatement findPayment = connection.prepareStatement(FIND_PAYMENT_BY_STATUS_AND_CABIN_STATUS);
            try {
                connection.setAutoCommit(false);
                createOrder.setInt(1, payment.getOrder().getUser().getId());
                createOrder.setInt(2, payment.getOrder().getCabinStatus().getId());
                createOrder.setDate(3, Date.valueOf(DATE_NOW));
                createOrder.setInt(4, ORDER_STATUS_IN_PROGRESS);
                createOrder.setInt(5, payment.getOrder().getCruise().getId());
                createOrder.execute();

                createPayment.setInt(1, payment.getOrder().getCabinStatus().getId());
                createPayment.setInt(2, ORDER_STATUS_IN_PROGRESS);
                createPayment.setInt(3, PAYMENT_STATUS_PAID);
                createPayment.setDate(4, Date.valueOf(DATE_NOW));
                createPayment.setInt(5, payment.getAmount());
                createPayment.execute();

                updateCabinStatus.setInt(1, CABIN_STATUS_STATEMENT_BUSY);
                updateCabinStatus.setInt(2, payment.getOrder().getCabinStatus().getId());
                updateCabinStatus.executeUpdate();

                findPayment.setInt(1, payment.getOrder().getCabinStatus().getId());
                findPayment.setInt(2, PAYMENT_STATUS_PAID);
                ResultSet resultSet = findPayment.executeQuery();
                resultSet.next();
                Payment resultSetToPayment = ResultSetMapper.mapResultSetToPayment(resultSet);
                connection.commit();
                LOGGER.info("The transaction for ordering and creating a payment is completed");
                return resultSetToPayment;
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info("Rollback of transaction for insert into Orders and Payments tables");
                throw new DataBaseRuntimeException(e);
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }
}
