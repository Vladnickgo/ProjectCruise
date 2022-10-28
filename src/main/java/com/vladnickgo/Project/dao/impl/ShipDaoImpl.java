package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.dao.ShipDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShipDaoImpl extends AbstractCrudDaoImpl<Ship> implements ShipDao {
    private static final Logger LOGGER = Logger.getLogger(ShipDaoImpl.class);
    private static final Integer STATUS_STATEMENT_AVAILABLE = 1;
    private static final LocalDate START_STATUS_DATE = LocalDate.now();
    private static final LocalDate END_STATUS_DATE = LocalDate.now().plusYears(1);


    private static final String INSERT_QUERY = "INSERT INTO ships(ship_name, passengers, number_of_staff, ship_image_source) " +
            "VALUES (?, ?, ?, ?); ";

    private static final String FIND_BY_ID = "SELECT * FROM ships WHERE ship_id = ?; ";

    private static final String FIND_ALL = "SELECT * FROM ships; ";

    private static final String FIND_ALL_BY_RECORDS_ON_PAGE_AND_FIRST_RECORD_AND_SORTING_PARAMETER = "SELECT * FROM " +
            "ships ORDER BY ? LIMIT ? OFFSET ?; ";

    private static final String COUNT_ALL = "SELECT count(*) as number_of_ships FROM ships; ";

    private static final String UPDATE = "UPDATE ships " +
            "SET ship_name=?, passengers=?, number_of_staff=?, ship_image_source=? " +
            "WHERE ship_id = ?; ";

    private static final String INSERT_INTO_CABINS_BY_CABIN_TYPE_ID_AND_SHIP_ID = "INSERT INTO cabins(cabin_type_id, ship_id) " +
            "VALUES (?,(SELECT max(ship_id) FROM ships)); ";

    private static final String INSERT_INTO_CABIN_STATUSES_BY_CABIN_ID = "INSERT INTO " +
            "cabin_statuses(cabin_id, status_start, status_end, status_statement_id) " +
            "VALUES ((SELECT max(cabin_id) FROM cabins),?,?,?)";

    public ShipDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE);
    }

    @Override
    protected Ship mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToShip(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Ship entity) throws SQLException {
        preparedStatement.setString(1, entity.getShipName());
        preparedStatement.setInt(2, entity.getPassengersCapacity());
        preparedStatement.setInt(3, entity.getNumberOfStaff());
        preparedStatement.setString(4, entity.getShipImage());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Ship entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    public void insertShipAndCabinsAndCabinStatuses(Ship ship, List<CabinRequestDto> cabinRequestDtoList) throws SQLException {
        Connection connection = connector.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertShip = connection.prepareStatement(INSERT_QUERY);
            PreparedStatement insertCabin = connection.prepareStatement(INSERT_INTO_CABINS_BY_CABIN_TYPE_ID_AND_SHIP_ID);
            PreparedStatement insertCabinStatus = connection.prepareStatement(INSERT_INTO_CABIN_STATUSES_BY_CABIN_ID);
            mapForInsertStatement(insertShip, ship);
            insertShip.execute();
            for (CabinRequestDto cabinRequestDto : cabinRequestDtoList) {
                for (int i = 0; i < cabinRequestDto.getNumberOfCabins(); i++) {

                    insertCabin.setInt(1, cabinRequestDto.getCabinTypeId());
                    insertCabin.execute();

                    insertCabinStatus.setDate(1, Date.valueOf(START_STATUS_DATE));
                    insertCabinStatus.setDate(2, Date.valueOf(END_STATUS_DATE));
                    insertCabinStatus.setInt(3, STATUS_STATEMENT_AVAILABLE);
                    insertCabinStatus.execute();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataBaseRuntimeException(e);
        }finally {
            connection.close();
        }
    }

    @Override
    public Integer countAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("number_of_ships");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<Ship> findAllByPageNumberAndItemsOnPage(ShipRequestDtoUtil shipRequestDtoUtil) {
        String sortingParameters = String.format("%s %s", shipRequestDtoUtil.getSorting(), shipRequestDtoUtil.getOrdering());
        Integer itemsOnPage = shipRequestDtoUtil.getItemsOnPage();
        Integer firstRecordOnPage = shipRequestDtoUtil.getFirstRecordOnPage();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_ALL_BY_RECORDS_ON_PAGE_AND_FIRST_RECORD_AND_SORTING_PARAMETER)) {
            preparedStatement.setString(1, sortingParameters);
            preparedStatement.setInt(2, itemsOnPage);
            preparedStatement.setInt(3, firstRecordOnPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Ship> entities = new HashSet<>();
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