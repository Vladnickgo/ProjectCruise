package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.PortDao;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import com.vladnickgo.Project.service.util.PortRequestDtoUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class PortDaoImpl extends AbstractCrudDaoImpl<Port> implements PortDao {
    private static final Logger LOGGER = Logger.getLogger(PortDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO ports(port_name_ua, port_name_en, country_ua, country_en) " +
            "VALUES (?,?,?,?);";

    private static final String FIND_BY_ID = "SELECT * FROM ports WHERE payment_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM ports;";

    private static final String UPDATE = "UPDATE ports " +
            "SET port_name_ua=?, port_name_en=?, country_ua=?, country_en=? " +
            "WHERE port_id = ?; ";

    private static final String FIND_ALL_BY_PAGE_AND_SORTING = "SELECT * FROM ports " +
            "ORDER BY %s %s LIMIT ? OFFSET ?;";

    private static final String COUNT_ALL = "SELECT count(*) AS number_of_ports FROM ports; ";

    private static final String FIND_BY_PORT_NAME_EN = "SELECT * FROM ports WHERE port_name_en = ?";

    private static final String FIND_BY_PORT_NAME_UA = "SELECT * FROM ports WHERE port_name_ua = ?";

    public PortDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected Port mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToPort(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Port entity) throws SQLException {
        preparedStatement.setString(1, entity.getPortNameUa());
        preparedStatement.setString(2, entity.getPortNameEn());
        preparedStatement.setString(3, entity.getCountryUa());
        preparedStatement.setString(4, entity.getCountryEn());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Port entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    public Optional<Port> findByNameUa(String portNameUa) {
        return findByStringParam(portNameUa, FIND_BY_PORT_NAME_UA);
    }

    @Override
    public void deletePortById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ports WHERE port_id = ?; ")
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("The port has been removed");
        } catch (SQLException e) {
            LOGGER.info("The request could not be completed");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Optional<Port> findByNameEn(String portNameEn) {
        return findByStringParam(portNameEn, FIND_BY_PORT_NAME_EN);
    }

    @Override
    public Integer countAll() {
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_ALL);
            resultSet.next();
            return resultSet.getInt("number_of_ports");
        } catch (SQLException e) {
            LOGGER.info("The request could not be completed");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<Port> findAllByPageAndSorting(PortRequestDtoUtil portRequestDtoUtil) {
        String sorting = portRequestDtoUtil.getSorting();
        String ordering = portRequestDtoUtil.getOrdering();
        String query = String.format(FIND_ALL_BY_PAGE_AND_SORTING, sorting, ordering);
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, portRequestDtoUtil.getItemsOnPage());
            preparedStatement.setInt(2, portRequestDtoUtil.getFirstRecordOnPage());
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Port> entities = new HashSet<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return new ArrayList<>(entities);
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }
}
