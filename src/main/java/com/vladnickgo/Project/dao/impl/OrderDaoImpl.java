package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.OrderDao;
import com.vladnickgo.Project.dao.entity.Order;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class OrderDaoImpl extends AbstractCrudDaoImpl<Order> implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO orders(user_id, user_document, cabin_status_id, order_date, order_status_id, cruise_id) " +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String FIND_BY_ID = "SELECT * FROM orders WHERE order_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM orders; ";

    private static final String UPDATE = "UPDATE orders " +
            "SET user_id=?, user_document=?, cabin_status_id=?, order_date=?, order_status_id=?, cruise_id=? " +
            "WHERE order_id = ?;";

    private static final String UPDATE_ORDER_STATUS_BY_PAYMENT_ID = "UPDATE orders " +
            "LEFT JOIN payments p on orders.order_id = p.order_id " +
            "SET orders.order_status_id=? " +
            "WHERE payment_id=?;";

    public static final String FIND_NUMBER_OF_BUSY_CABINS_FOR_EACH_CABIN_TYPE = "SELECT cabin_type_name, count(*) AS number_of_cabins " +
            "FROM orders o " +
            "LEFT JOIN cruises c on c.cruise_id = o.cruise_id " +
            "LEFT JOIN payments p on o.order_id = p.order_id " +
            "LEFT JOIN ships s on s.ship_id = c.ship_id " +
            "LEFT JOIN cabin_statuses cs on cs.cabin_status_id = o.cabin_status_id " +
            "LEFT JOIN cabins c2 on c2.cabin_id = cs.cabin_id " +
            "LEFT JOIN cabin_types ct on ct.cabin_type_id = c2.cabin_type_id " +
            "WHERE c.cruise_id = ? " +
            "GROUP BY ct.cabin_type_id;";

    public OrderDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected Order mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToOrder(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Order entity) throws SQLException {
        preparedStatement.setInt(1, entity.getUser().getId());
        preparedStatement.setString(2, entity.getUserDocuments());
        preparedStatement.setInt(3, entity.getCabinStatus().getId());
        preparedStatement.setDate(4, Date.valueOf(entity.getOrderDate()));
        preparedStatement.setInt(5, entity.getOrderStatus().getId());
        preparedStatement.setInt(6, entity.getCruise().getId());

    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Order entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(7, entity.getId());
    }

    @Override
    public Map<String, Integer> getEachBusyCabinTypeNumberMap(Integer cruiseId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NUMBER_OF_BUSY_CABINS_FOR_EACH_CABIN_TYPE)) {
            preparedStatement.setInt(1, cruiseId);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<String, Integer> entity = new HashMap<>();
                String cabinTypeName;
                int numberOfCabins;
                while (resultSet.next()) {
                    cabinTypeName = resultSet.getString("cabin_type_name");
                    numberOfCabins = resultSet.getInt("number_of_cabins");
                    entity.put(cabinTypeName, numberOfCabins);
                }
                return entity;
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void updateOrderStatusByPaymentId(Integer paymentId, Integer orderStatusId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_BY_PAYMENT_ID)) {
            preparedStatement.setInt(1, orderStatusId);
            preparedStatement.setInt(2, paymentId);
            preparedStatement.executeUpdate();
            LOGGER.info("Order updated");
        } catch (SQLException e) {
            LOGGER.info("Order not updated");
            throw new DataBaseRuntimeException(e);
        }
    }
}
