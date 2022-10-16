package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.CabinStatusDao;
import com.vladnickgo.Project.dao.entity.CabinStatus;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CabinStatusDaoImpl extends AbstractCrudDaoImpl<CabinStatus> implements CabinStatusDao {
    private static final Logger LOGGER = Logger.getLogger(CabinStatusDao.class);

    private static final String INSERT_QUERY = "INSERT INTO cabin_statuses(cabin_id, status_start, status_end, status_statement_id) " +
            "VALUES(?, ?, ?, ?) ";

    private static final String FIND_BY_ID = "SELECT * FROM cabin_statuses WHERE cabin_status_id=?";

    private static final String FIND_ALL = "SELECT * FROM cabin_statuses; ";

    private static final String UPDATE = "UPDATE cabin_statuses SET cabin_id=?, status_start=?, status_end=?, status_statement_id=? " +
            "WHERE cabin_status_id=?; ";

    private static final String FIND_BY_PARAMETERS = "SELECT * FROM cabin_statuses " +
            "LEFT JOIN cabins c on c.cabin_id = cabin_statuses.cabin_id " +
            "LEFT JOIN cruises c2 on c.ship_id = c2.ship_id " +
            "WHERE status_start = date_start AND status_end = date_end " +
            "AND cruise_id = ? AND cabin_type_id = ? LIMIT 1;";

    private static final String FIND_CABIN_STATUSES_BY_PARAMETERS = "SELECT *, count(*) AS number_of_room " +
            "FROM cabin_statuses cs " +
            "         LEFT JOIN cabin_status_statements css on cs.status_statement_id = css.status_statement_id " +
            "         LEFT OUTER JOIN cabins c on c.cabin_id = cs.cabin_id " +
            "         LEFT JOIN cabin_types ct on ct.cabin_type_id = c.cabin_type_id " +
            "         LEFT OUTER JOIN ships s on s.ship_id = c.ship_id " +
            "         LEFT JOIN cruises c2 on c.ship_id = c2.ship_id " +
            "WHERE status_start = date_start " +
            "  AND status_end = date_end " +
            "  AND cruise_id = ? " +
            "  AND cs.status_statement_id = ? " +
            "GROUP BY c.cabin_type_id; ";

    private static final String FIND_CABIN_STATUS_ID_BY_PARAMETERS = "SELECT cabin_status_id " +
            "FROM cabin_statuses " +
            "         LEFT OUTER JOIN cabins c on c.cabin_id = cabin_statuses.cabin_id " +
            "         LEFT JOIN cabin_types ct on ct.cabin_type_id = c.cabin_type_id " +
            "         LEFT OUTER JOIN cruises c2 on c.ship_id = c2.ship_id " +
            "WHERE status_statement_id = ? " +
            "  AND c.cabin_type_id = ? " +
            "  AND cruise_id = ? " +
            "LIMIT 1;";


    private static final Integer AVAILABLE_STATUS_STATEMENT = 2;

    public CabinStatusDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected CabinStatus mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToCabinStatus(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, CabinStatus entity) throws SQLException {
        preparedStatement.setInt(1, entity.getCabin().getId());
        preparedStatement.setDate(2, Date.valueOf(entity.getStatusStart()));
        preparedStatement.setDate(3, Date.valueOf(entity.getStatusEnd()));
        preparedStatement.setInt(4, entity.getStatusStatement().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, CabinStatus entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    public CabinStatus findCabinStatusByParameters(Integer cruiseId, Integer cabinTypeId) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PARAMETERS)) {
            preparedStatement.setInt(1, cruiseId);
            preparedStatement.setInt(2, cabinTypeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapResultSetToEntity(resultSet);
        } catch (SQLException e) {
            LOGGER.info("DataBase exception");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Map<CabinStatus, Integer> getCabinStatusListByParameter(Integer cruiseId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CABIN_STATUSES_BY_PARAMETERS)) {
            preparedStatement.setInt(1, cruiseId);
            preparedStatement.setInt(2, AVAILABLE_STATUS_STATEMENT);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<CabinStatus, Integer> entities = new HashMap<>();
                while (resultSet.next()) {
                    entities.put(mapResultSetToEntity(resultSet), resultSet.getInt("number_of_room"));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.info("DataBase exception");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer findCabinStatusIdByParameters(Integer cruiseStatusId, Integer cabinStatusId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CABIN_STATUS_ID_BY_PARAMETERS)) {
            preparedStatement.setInt(1, AVAILABLE_STATUS_STATEMENT);
            preparedStatement.setInt(2, cabinStatusId);
            preparedStatement.setInt(3, cruiseStatusId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt("cabin_status_id"):null;
        } catch (SQLException e) {
            LOGGER.info("DataBase exception");
            throw new DataBaseRuntimeException(e);
        }
    }
}
