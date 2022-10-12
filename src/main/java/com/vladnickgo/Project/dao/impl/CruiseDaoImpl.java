package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CruiseDaoImpl extends AbstractCrudDaoImpl<Cruise> implements CruiseDao {

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

    private static final String FIND_ALL_BY_PARAM = "SELECT * FROM cruises " +
            "LEFT JOIN routes r on r.route_id = cruises.route_id " +
            "LEFT JOIN cruise_statuses cs on cs.cruise_status_id = cruises.cruise_status_id " +
            "LEFT JOIN ships s on cruises.ship_id = s.ship_id " +
            "ORDER BY cruise_name LIMIT ? OFFSET ?";

    private static final String COUNT_ALL = "SELECT count(*) AS number_of_cruises FROM cruises";

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
    public List<Cruise> findAllByParameters(Integer firstRecordOnPage, Integer itemsOnPage) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PARAM)) {
            preparedStatement.setInt(1, itemsOnPage);
            preparedStatement.setInt(2, firstRecordOnPage);
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
}
