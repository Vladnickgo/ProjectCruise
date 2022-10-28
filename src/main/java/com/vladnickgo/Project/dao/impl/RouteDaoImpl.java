package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.RouteDao;
import com.vladnickgo.Project.dao.entity.Route;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import com.vladnickgo.Project.service.util.RouteRequestDtoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteDaoImpl extends AbstractCrudDaoImpl<Route> implements RouteDao {

    private static final String INSERT_QUERY = "INSERT INTO routes(route_name) " +
            "VALUES (?); ";

    private static final String FIND_BY_ID = "SELECT * FROM routes WHERE rout_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM routes; ";

    private static final String UPDATE = "UPDATE routes SET route_name = ? WHERE route_id = ?; ";

    private static final String FIND_ALL_BY_NUMBER_OF_PAGE = "SELECT * FROM routes LIMIT ? OFFSET ?; ";

    private static final String COUNT_ALL = "SELECT count(*) AS number_of_routes FROM routes; ";


    public RouteDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }


    @Override
    protected Route mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToRoute(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Route entity) throws SQLException {
        preparedStatement.setString(1, entity.getRouteName());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Route entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }

    @Override
    public List<Route> findAllByNumberOfPageAndSorting(RouteRequestDtoUtil routeRequestDtoUtil) {
        Integer itemsOnPage = routeRequestDtoUtil.getItemsOnPage();
        Integer firstRecordOnPage = routeRequestDtoUtil.getFirstRecordOnPage();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_ALL_BY_NUMBER_OF_PAGE)) {
            preparedStatement.setInt(1, itemsOnPage);
            preparedStatement.setInt(2, firstRecordOnPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Route> entities = new HashSet<>();
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
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("number_of_routes");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }
}
