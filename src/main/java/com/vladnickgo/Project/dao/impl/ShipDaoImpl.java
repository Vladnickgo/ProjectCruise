package com.vladnickgo.Project.dao.impl;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.controller.dto.CabinRequestDto;
import com.vladnickgo.Project.controller.dto.LocalDateDto;
import com.vladnickgo.Project.dao.ShipDao;
import com.vladnickgo.Project.dao.entity.Ship;
import com.vladnickgo.Project.dao.exception.DataBaseRuntimeException;
import com.vladnickgo.Project.dao.mapper.ResultSetMapper;
import com.vladnickgo.Project.service.util.ShipRequestDtoUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ShipDaoImpl extends AbstractCrudDaoImpl<Ship> implements ShipDao {
    private static final Logger LOGGER = Logger.getLogger(ShipDaoImpl.class);
    private static final Integer STATUS_STATEMENT_AVAILABLE = 1;
    private static final Integer CABIN_STATUS_ACTIVE = 1;
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
            "VALUES (?,?); ";

    public static final String SELECT_MAX_CABIN_ID_FROM_CABINS = "SELECT max(cabin_id) as last_added_cabin_id FROM cabins; ";

    private static final String INSERT_INTO_CABIN_STATUSES_BY_CABIN_ID = "INSERT INTO " +
            "cabin_statuses(cabin_id, status_start, status_end, status_statement_id) " +
            "VALUES (?,?,?,?); ";

    private static final String FIND_ALL_BY_DATE_START_DATE_END = "SELECT * FROM ships " +
            "LEFT JOIN cabins c on ships.ship_id = c.ship_id " +
            "LEFT JOIN cabin_statuses cs on c.cabin_id = cs.cabin_id " +
            "WHERE status_start < ? AND status_end > ? AND status_statement_id = ? " +
            "GROUP BY ship_name; ";

    private static final String DELETE_SHIP_BY_ID = "DELETE FROM ships WHERE ship_id = ?; ";

    private static final String FIND_NUMBER_OF_CRUISES_FOR_SHIPS = "SELECT ship_name,count(*) AS number_of_cruises FROM ships " +
            "LEFT JOIN cruises c on ships.ship_id = c.ship_id " +
            "WHERE cruise_id is not null AND (date_start BETWEEN ? AND ? OR date_end BETWEEN ? AND ?) " +
            "GROUP BY ships.ship_id; ";

    public static final String SELECT_MAX_SHIP_ID_AS_LAST_ADDED_ID_FROM_SHIPS = "SELECT max(ship_id) as last_added_ship_id " +
            "FROM ships;";

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
            LOGGER.info("Start transaction");
            PreparedStatement insertShip = connection.prepareStatement(INSERT_QUERY);
            PreparedStatement findLastAddedShipId = connection.prepareStatement(SELECT_MAX_SHIP_ID_AS_LAST_ADDED_ID_FROM_SHIPS);
            PreparedStatement findLastAddedCabinId = connection.prepareStatement(SELECT_MAX_CABIN_ID_FROM_CABINS);
            PreparedStatement insertCabin = connection.prepareStatement(INSERT_INTO_CABINS_BY_CABIN_TYPE_ID_AND_SHIP_ID);
            PreparedStatement insertCabinStatus = connection.prepareStatement(INSERT_INTO_CABIN_STATUSES_BY_CABIN_ID);

            mapForInsertStatement(insertShip, ship);
            insertShip.executeUpdate();
            LOGGER.info("Ship inserted");

            ResultSet resultSetLastAddedShipId = findLastAddedShipId.executeQuery();
            resultSetLastAddedShipId.next();
            int last_added_ship_id = resultSetLastAddedShipId.getInt("last_added_ship_id");
            LOGGER.info("last_added_ship_id: " + last_added_ship_id);

            int last_added_cabin_id = 0;
            for (CabinRequestDto cabinRequestDto : cabinRequestDtoList) {
                LOGGER.info("cabinRequestDto: " + cabinRequestDto);
                for (int i = 0; i < cabinRequestDto.getNumberOfCabins(); i++) {
                    LOGGER.info("i = " + i);
                    insertCabin.setInt(1, cabinRequestDto.getCabinTypeId());
                    insertCabin.setInt(2, last_added_ship_id);
                    insertCabin.execute();
                    ResultSet resultSetLastAddedCabinId = findLastAddedCabinId.executeQuery();
                    resultSetLastAddedCabinId.next();
                    last_added_cabin_id = resultSetLastAddedCabinId.getInt("last_added_cabin_id");
                    LOGGER.info("Cabin inserted id=" + last_added_cabin_id);

                    insertCabinStatus.setInt(1, last_added_cabin_id);
                    insertCabinStatus.setDate(2, Date.valueOf(START_STATUS_DATE));
                    insertCabinStatus.setDate(3, Date.valueOf(END_STATUS_DATE));
                    insertCabinStatus.setInt(4, STATUS_STATEMENT_AVAILABLE);
                    insertCabinStatus.executeUpdate();

                }
            }
            connection.commit();
            LOGGER.info("The insert ship, cabins and cabin statuses transaction has been committed");
        } catch (SQLException e) {
            connection.rollback();
            LOGGER.info("Rollback of the insert ship, cabins and cabin statuses transaction");
            throw new DataBaseRuntimeException(e);
        } finally {
            LOGGER.info("Close connection");
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

    @Override
    public List<Ship> findAllFreeShipsByDateStartAndDateEnd(LocalDate dateStart, LocalDate dateEnd) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_ALL_BY_DATE_START_DATE_END)) {
            preparedStatement.setDate(1, Date.valueOf(dateStart));
            preparedStatement.setDate(2, Date.valueOf(dateEnd));
            preparedStatement.setInt(3, CABIN_STATUS_ACTIVE);
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

    @Override
    public Map<String, Integer> getNumberOfCruisesForShips(LocalDateDto localDateDto) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NUMBER_OF_CRUISES_FOR_SHIPS)) {
            preparedStatement.setDate(1, Date.valueOf(localDateDto.getDateStart()));
            preparedStatement.setDate(2, Date.valueOf(localDateDto.getDateEnd()));
            preparedStatement.setDate(3, Date.valueOf(localDateDto.getDateStart()));
            preparedStatement.setDate(4, Date.valueOf(localDateDto.getDateEnd()));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<String, Integer> entities = new HashMap<>();
                while (resultSet.next()) {
                    entities.put(resultSet.getString("ship_name"), resultSet.getInt("number_of_cruises"));
                }
                return entities;
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void deleteShipBtId(Integer shipId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SHIP_BY_ID)) {
            preparedStatement.setInt(1, shipId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Can't delete ship");
            throw new DataBaseRuntimeException(e);
        }
    }
}