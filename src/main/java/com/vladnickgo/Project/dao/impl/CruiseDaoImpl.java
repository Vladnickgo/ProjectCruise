package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import com.vladnickgo.Project.service.util.CruiseRequestDtoUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CruiseDaoImpl extends AbstractCrudDaoImpl<Cruise> implements CruiseDao {
    private static final Logger LOGGER = Logger.getLogger(CruiseDaoImpl.class);
    private static final Integer CRUISE_STATUS_AVAILABLE = 1;
    private static final Integer CABIN_STATUS_STATEMENT_ACTIVE = 1;
    private static final Integer CABIN_STATUS_STATEMENT_AVAILABLE = 2;

    private static final String INSERT_QUERY = "INSERT INTO cruises(cruise_name, route_id, date_start, date_end, cruise_status_id, ship_id) " +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String FIND_BY_ID = "SELECT * FROM cruises " +
            "LEFT JOIN routes r on r.route_id = cruises.route_id " +
            "LEFT JOIN cruise_statuses cs on cs.cruise_status_id = cruises.cruise_status_id " +
            "LEFT JOIN ships s on cruises.ship_id = s.ship_id " +
            "WHERE cruise_id = ?;";

    private static final String FIND_ALL = "SELECT * FROM cruises;";

    private static final String UPDATE_CRUISE = "UPDATE cruises " +
            "SET cruise_name=?, route_id=?, date_start=?, date_end=?, cruise_status_id=?, ship_id=? " +
            "WHERE cruise_id = ? ;";

    private static final String FIND_ALL_BY_PARAM = "SELECT * FROM cruises c " +
            "LEFT JOIN routes r on r.route_id = c.route_id " +
            "LEFT JOIN cruise_statuses cs on cs.cruise_status_id = c.cruise_status_id " +
            "LEFT JOIN ships s on c.ship_id = s.ship_id " +
            "WHERE c.cruise_status_id=? OR c.cruise_status_id=? OR c.cruise_status_id=? OR c.cruise_status_id=? " +
            "ORDER BY ? " +
            "LIMIT ? OFFSET ?; ";

    private static final String FIND_CABIN_STATUS_DATE_END = "SELECT status_end as temp_status_end FROM cabin_statuses " +
            "LEFT JOIN cabins c on c.cabin_id = cabin_statuses.cabin_id " +
            "LEFT OUTER JOIN ships s on s.ship_id = c.ship_id " +
            "WHERE status_start <= ? AND status_end >= ? AND status_statement_id = ? " +
            "GROUP BY status_end; ";

    private static final String FIND_ALL_CABIN_STATUSES_BY_SHIP_ID_DATE_START_DATE_END_STATUS_STATEMENT_ID = "SELECT * " +
            "FROM cabin_statuses " +
            "LEFT JOIN cabins c on c.cabin_id = cabin_statuses.cabin_id " +
            "LEFT OUTER JOIN ships s on s.ship_id = c.ship_id " +
            "WHERE s.ship_id=? AND status_start<=? AND status_end>=? AND status_statement_id=? ;";

    private static final String UPDATE_CABIN_STATUS_BY_SHIP_ID_STATUS_START_AND_STATUS_END = "UPDATE cabin_statuses " +
            "LEFT JOIN cabins c on c.cabin_id = cabin_statuses.cabin_id " +
            "LEFT OUTER JOIN ships s on s.ship_id = c.ship_id " +
            "SET status_end = ? " +
            "WHERE s.ship_id = ? AND status_start <= ? AND status_end >= ? AND status_statement_id = ?; ";

    private static final String INSERT_INTO_CABIN_STATUS_BY_CABIN_ID_DATE_START_DATE_END = "INSERT " +
            "INTO cabin_statuses(cabin_id, status_start, status_end, status_statement_id) " +
            "VALUES (?, ?, ?, ?), " +
            "       (?, ?, ?, ?); ";

    private static final String COUNT_ALL = "SELECT count(*) AS number_of_cruises FROM cruises";

    private static final String UPDATE_CRUISE_STATUS_BY_ID = "UPDATE cruises " +
            "SET cruise_status_id = ? WHERE cruise_id = ?";

    private static final String FIND_MAX_CRUISE_DURATION = "SELECT max(nights)+1 AS max_cruise_duration FROM cruises; ";

    private static final String FIND_MIN_CRUISE_DURATION = "SELECT min(nights)+1 AS min_cruise_duration FROM cruises; ";


    public CruiseDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_CRUISE);
    }

    @Override
    protected Cruise mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToCruise(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Cruise entity) throws SQLException {
        preparedStatement.setString(1, entity.getCruiseName());
        preparedStatement.setInt(2, entity.getRoute().getId());
        preparedStatement.setDate(3, Date.valueOf(entity.getDateStart()));
        preparedStatement.setDate(4, Date.valueOf(entity.getDateEnd()));
        preparedStatement.setInt(5, entity.getCruiseStatus().getId());
        preparedStatement.setInt(6, entity.getShip().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Cruise entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(7, entity.getId());
    }

    @Override
    public List<Cruise> findAllByParameters(CruiseRequestDtoUtil cruiseRequestDtoUtil) {
        Integer[] paymentStatusIds = cruiseRequestDtoUtil.getPaymentStatusIds();

        String sorting = cruiseRequestDtoUtil.getSorting();
        String ordering = cruiseRequestDtoUtil.getOrdering();
        Integer itemsOnPage = cruiseRequestDtoUtil.getItemsOnPage();
        String orderParameters = String.format(("%s %s"), sorting, ordering);
        Integer firstRecordOnPage = cruiseRequestDtoUtil.getFirstRecordOnPage();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PARAM)) {
            preparedStatement.setInt(1, paymentStatusIds[0]);
            preparedStatement.setInt(2, paymentStatusIds[1]);
            preparedStatement.setInt(3, paymentStatusIds[2]);
            preparedStatement.setInt(4, paymentStatusIds[3]);
            preparedStatement.setString(5, orderParameters);
            preparedStatement.setInt(6, itemsOnPage);
            preparedStatement.setInt(7, firstRecordOnPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Cruise> entities = new HashSet<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return new ArrayList<>(entities);
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }


    @Override
    public Integer countAll() {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("number_of_cruises");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void createCruise(Cruise cruise) throws SQLException {
        Connection connection = connector.getConnection();
        try {
            PreparedStatement saveCruise = connection.prepareStatement(INSERT_QUERY);
            PreparedStatement findCabinStatusDateEnd = connection.prepareStatement(FIND_CABIN_STATUS_DATE_END);
            PreparedStatement findAllCabinStatusesByShipIdDateStartDateEndAndStatusStatementId = connection.prepareStatement(FIND_ALL_CABIN_STATUSES_BY_SHIP_ID_DATE_START_DATE_END_STATUS_STATEMENT_ID);
            PreparedStatement updateCabinStatusByShipIdStatusStartStatusEnd = connection.prepareStatement(UPDATE_CABIN_STATUS_BY_SHIP_ID_STATUS_START_AND_STATUS_END);
            PreparedStatement insertIntoCabinStatusByShipIdStatusStartStatusEnd = connection.prepareStatement(INSERT_INTO_CABIN_STATUS_BY_CABIN_ID_DATE_START_DATE_END);

            connection.setAutoCommit(false);
            saveCruise.setString(1, cruise.getCruiseName());
            saveCruise.setInt(2, cruise.getRoute().getId());
            saveCruise.setDate(3, Date.valueOf(cruise.getDateStart()));
            saveCruise.setDate(4, Date.valueOf(cruise.getDateEnd()));
            saveCruise.setInt(5, CRUISE_STATUS_AVAILABLE);
            saveCruise.setInt(6, cruise.getShip().getId());
            saveCruise.executeUpdate();

            findCabinStatusDateEnd.setDate(1, Date.valueOf(cruise.getDateStart()));
            findCabinStatusDateEnd.setDate(2, Date.valueOf(cruise.getDateEnd()));
            findCabinStatusDateEnd.setInt(3, CABIN_STATUS_STATEMENT_ACTIVE);
            ResultSet resultSet = findCabinStatusDateEnd.executeQuery();
            resultSet.next();
            Date temp_status_end = resultSet.getDate("temp_status_end");

            findAllCabinStatusesByShipIdDateStartDateEndAndStatusStatementId.setInt(1, cruise.getShip().getId());
            findAllCabinStatusesByShipIdDateStartDateEndAndStatusStatementId.setDate(2, Date.valueOf(cruise.getDateStart()));
            findAllCabinStatusesByShipIdDateStartDateEndAndStatusStatementId.setDate(3, Date.valueOf(cruise.getDateEnd()));
            findAllCabinStatusesByShipIdDateStartDateEndAndStatusStatementId.setInt(4, CABIN_STATUS_STATEMENT_ACTIVE);
            try (final ResultSet findAllResultSet = findAllCabinStatusesByShipIdDateStartDateEndAndStatusStatementId.executeQuery()) {
                while (findAllResultSet.next()) {
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setInt(1, findAllResultSet.getInt("cabin_statuses.cabin_id"));
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setDate(2, Date.valueOf(cruise.getDateStart()));
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setDate(3, Date.valueOf(cruise.getDateEnd()));
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setInt(4, CABIN_STATUS_STATEMENT_AVAILABLE);
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setInt(5, findAllResultSet.getInt("cabin_statuses.cabin_id"));
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setDate(6, Date.valueOf(cruise.getDateEnd()));
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setDate(7, temp_status_end);
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.setInt(8, CABIN_STATUS_STATEMENT_ACTIVE);
                    insertIntoCabinStatusByShipIdStatusStartStatusEnd.executeUpdate();
                }
            }
            updateCabinStatusByShipIdStatusStartStatusEnd.setDate(1, Date.valueOf(cruise.getDateStart()));
            updateCabinStatusByShipIdStatusStartStatusEnd.setInt(2, cruise.getShip().getId());
            updateCabinStatusByShipIdStatusStartStatusEnd.setDate(3, Date.valueOf(cruise.getDateStart()));
            updateCabinStatusByShipIdStatusStartStatusEnd.setDate(4, Date.valueOf(cruise.getDateEnd()));
            updateCabinStatusByShipIdStatusStartStatusEnd.setInt(5, CABIN_STATUS_STATEMENT_ACTIVE);
            updateCabinStatusByShipIdStatusStartStatusEnd.executeUpdate();
            connection.commit();
            LOGGER.info("Transaction has been rollback");
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info("Rollback of transaction");
            throw new DataBaseRuntimeException(e);
        } finally {
            LOGGER.info("Connection is closed");
            connection.close();
        }

    }

    @Override
    public void updateCruiseStatusByCruiseId(Integer cruiseId, Integer cruiseStatusId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CRUISE_STATUS_BY_ID)) {
            preparedStatement.setInt(1, cruiseStatusId);
            preparedStatement.setInt(2, cruiseId);
            preparedStatement.executeUpdate();
            LOGGER.info("Cruise has been blocked");
        } catch (SQLException e) {
            LOGGER.info("Cruise not blocked");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer getMaxCruiseDuration() {
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(FIND_MAX_CRUISE_DURATION);
            resultSet.next();
            int max_cruise_duration = resultSet.getInt("max_cruise_duration");
            LOGGER.info("Max cruise duration is equal " + max_cruise_duration);
            return max_cruise_duration;
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer getMinCruiseDuration() {
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(FIND_MIN_CRUISE_DURATION);
            resultSet.next();
            int max_cruise_duration = resultSet.getInt("min_cruise_duration");
            LOGGER.info("Max cruise duration is equal " + max_cruise_duration);
            return max_cruise_duration;
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }
}
