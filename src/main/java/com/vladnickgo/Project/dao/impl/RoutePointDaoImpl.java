package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.RoutePointDao;
import com.vladnickgo.Project.dao.entity.RoutePoint;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoutePointDaoImpl extends AbstractCrudDaoImpl<RoutePoint> implements RoutePointDao {
    private static final Logger LOGGER = Logger.getLogger(RoutePointDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO route_points(route_id, day_number, port_id) " +
            "VALUES (?,?,?);";

    private static final String FIND_BY_ID = "SELECT * " +
            "FROM route_points " +
            "LEFT JOIN ports p on p.port_id = route_points.port_id " +
            "LEFT JOIN routes r on r.route_id = route_points.route_id " +
            "WHERE route_point_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM route_points " +
            "LEFT JOIN ports p on p.port_id = route_points.port_id " +
            "LEFT JOIN routes r on r.route_id = route_points.route_id ; ";

    private static final String UPDATE = "UPDATE route_points " +
            "SET route_id=?, day_number=?, port_id=? " +
            "WHERE route_point_id = ?; ";

    private static final String FIND_ALL_BY_ROUTE_ID = "SELECT * FROM route_points " +
            "LEFT JOIN routes r on r.route_id = route_points.route_id " +
            "LEFT JOIN ports p on p.port_id = route_points.port_id " +
            "WHERE r.route_id = ?; ";

    private static final String DELETE_BY_ID = "DELETE FROM route_points " +
            "WHERE route_point_id = ?;";

    public RoutePointDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected RoutePoint mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToRoutePoint(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, RoutePoint entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRoute().getId());
        preparedStatement.setInt(2, entity.getDayNumber());
        preparedStatement.setInt(3, entity.getPort().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, RoutePoint entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(4, entity.getId());
    }


    @Override
    public List<RoutePoint> findAllByRouteId(Integer routeId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ROUTE_ID)) {
            preparedStatement.setInt(1, routeId);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<RoutePoint> entities = new HashSet<>();
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
    public void deleteById(Integer routePointId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            preparedStatement.setInt(1, routePointId);
            preparedStatement.executeUpdate();
            LOGGER.info(String.format("Route point with id=%d has been deleted", routePointId));
        } catch (SQLException e) {
            LOGGER.info("Route point not deleted");
            throw new DataBaseRuntimeException(e);
        }
    }
}
