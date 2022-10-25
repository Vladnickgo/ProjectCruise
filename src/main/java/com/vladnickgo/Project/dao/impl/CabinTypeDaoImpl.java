package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.controller.dto.CabinTypeRequestDto;
import com.vladnickgo.Project.dao.CabinTypeDao;
import com.vladnickgo.Project.dao.entity.CabinType;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CabinTypeDaoImpl extends AbstractCrudDaoImpl<CabinType> implements CabinTypeDao {

    private static final String INSERT_QUERY = "INSERT INTO cabin_types(cabin_type_name, number_of_beds, price) " +
            "VALUES (?, ?, ?);";

    private static final String FIND_BY_ID = "SELECT * FROM cabin_types WHERE cabin_type_id = ?;";

    private static final String FIND_ALL = "SELECT * FROM cabin_types;";

    private static final String UPDATE = "UPDATE cabin_types SET cabin_type_name=?, number_of_beds=?, price=? " +
            "WHERE cabin_type_id = ? ";

    public CabinTypeDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected CabinType mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToCabinType(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, CabinType entity) throws SQLException {
        preparedStatement.setString(1, entity.getCabinTypeName());
        preparedStatement.setInt(2, entity.getNumberOfBeds());
        preparedStatement.setInt(3, entity.getPrice());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, CabinType entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(4, entity.getId());
    }

    @Override
    public void insertCabinTypesByCabinTypeNumbers(CabinTypeRequestDto cabinTypeRequestDto) {

    }
}
