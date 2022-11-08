package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.dao.CabinDao;
import com.vladnickgo.Project.dao.entity.Cabin;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CabinDaoImpl extends AbstractCrudDaoImpl<Cabin> implements CabinDao {

    private static final String INSERT_QUERY = "INSERT INTO cabins(cabin_type_id, cabin_status_id, ship_id) " +
            "VALUES (?, ?, ?); ";

    private static final String FIND_BY_ID = "SELECT * FROM cabins WHERE cabin_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM cabins; ";

    private static final String UPDATE = "UPDATE cabins SET cabin_type_id =?,cabin_status_id=?,ship_id=? " +
            "WHERE cabin_id = ?; ";

    public CabinDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected Cabin mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToCabin(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Cabin entity) throws SQLException {
        preparedStatement.setInt(1, entity.getCabinType().getId());
        preparedStatement.setInt(2, entity.getShip().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Cabin entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(3, entity.getId());
    }

}