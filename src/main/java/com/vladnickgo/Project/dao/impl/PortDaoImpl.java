package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.PortDao;
import com.vladnickgo.Project.dao.entity.Port;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PortDaoImpl extends AbstractCrudDaoImpl<Port> implements PortDao {

    private static final String INSERT_QUERY = "INSERT INTO ports(port_name_ua, port_name_en, country_ua, country_en) " +
            "VALUES (?,?,?,?);";

    private static final String FIND_BY_ID = "SELECT * FROM ports WHERE payment_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM ports;";

    private static final String UPDATE = "UPDATE ports " +
            "SET port_name_ua=?, port_name_en=?, country_ua=?, country_en=? " +
            "WHERE port_id = ?; ";

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
}
