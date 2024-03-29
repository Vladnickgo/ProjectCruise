package com.vladnickgo.Project.dao.mapper;

import com.vladnickgo.Project.dao.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {

    public static User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return User.newBuilder()
                .id(resultSet.getInt("user_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .salt(resultSet.getString("salt"))
                .role(Role.getRole(resultSet.getInt("role_id")))
                .build();
    }

    public static Cruise mapResultSetToCruise(ResultSet resultSet) throws SQLException {
        return Cruise.newBuilder()
                .id(resultSet.getInt("cruise_id"))
                .cruiseName(resultSet.getString("cruise_name"))
                .route(mapResultSetToRoute(resultSet))
                .dateStart(resultSet.getDate("date_start").toLocalDate())
                .dateEnd(resultSet.getDate("date_end").toLocalDate())
                .nights(resultSet.getInt("nights"))
                .cruiseStatus(mapResultSetToCruiseStatus(resultSet))
                .ship(mapResultSetToShip(resultSet))
                .build();
    }

    public static Route mapResultSetToRoute(ResultSet resultSet) throws SQLException {
        return Route.newBuilder()
                .id(resultSet.getInt("route_id"))
                .routeName(resultSet.getString("route_name"))
                .build();
    }

    public static CruiseStatus mapResultSetToCruiseStatus(ResultSet resultSet) throws SQLException {
        return CruiseStatus.newBuilder()
                .id(resultSet.getInt("cruise_status_id"))
                .cruiseStatusName(resultSet.getString("cruise_status_name"))
                .build();
    }

    public static Ship mapResultSetToShip(ResultSet resultSet) throws SQLException {
        return Ship.newBuilder()
                .id(resultSet.getInt("ship_id"))
                .shipName(resultSet.getString("ship_name"))
                .passengersCapacity(resultSet.getInt("passengers"))
                .numberOfStaff(resultSet.getInt("number_of_staff"))
                .shipImageSource(resultSet.getString("ship_image_source"))
                .build();
    }

    public static Cabin mapResultSetToCabin(ResultSet resultSet) throws SQLException {
        return Cabin.newBuilder()
                .id(resultSet.getInt("cabin_id"))
                .cabinType(mapResultSetToCabinType(resultSet))
                .ship(mapResultSetToShip(resultSet))
                .build();
    }

    public static CabinType mapResultSetToCabinType(ResultSet resultSet) throws SQLException {
        return CabinType.newBuilder()
                .id(resultSet.getInt("cabin_type_id"))
                .cabinTypeName(resultSet.getString("cabin_type_name"))
                .numberOfBeds(resultSet.getInt("number_of_beds"))
                .price(resultSet.getInt("price"))
                .build();
    }

    public static CabinStatus mapResultSetToCabinStatus(ResultSet resultSet) throws SQLException {
        return CabinStatus.newBuilder()
                .id(resultSet.getInt("cabin_status_id"))
                .cabin(mapResultSetToCabin(resultSet))
                .statusStart(resultSet.getDate("status_start").toLocalDate())
                .statusEnd(resultSet.getDate("status_end").toLocalDate())
                .statusStatement(mapResultSetToCabinStatusStatement(resultSet))
                .build();
    }

    public static CabinStatusStatement mapResultSetToCabinStatusStatement(ResultSet resultSet) throws SQLException {
        return CabinStatusStatement.newBuilder()
                .id(resultSet.getInt("status_statement_id"))
                .statusStatementName(resultSet.getString("status_statement_name"))
                .build();
    }

    public static OrderStatus mapResultSetToOrderStatus(ResultSet resultSet) throws SQLException {
        return OrderStatus.newBuilder()
                .id(resultSet.getInt("status_statement_id"))
                .orderStatusName(resultSet.getString("order_status_name"))
                .build();
    }

    public static Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        return Order.newBuilder()
                .id(resultSet.getInt("order_id"))
                .user(mapResultSetToUser(resultSet))
                .userDocuments(resultSet.getString("user_document"))
                .orderDate(resultSet.getDate("order_date").toLocalDate())
                .cabinStatus(mapResultSetToCabinStatus(resultSet))
                .orderStatus(mapResultSetToOrderStatus(resultSet))
                .cruise(mapResultSetToCruise(resultSet))
                .build();
    }

    public static Payment mapResultSetToPayment(ResultSet resultSet) throws SQLException {
        return Payment.newBuilder()
                .id(resultSet.getInt("payment_id"))
                .order(mapResultSetToOrder(resultSet))
                .paymentDate(resultSet.getDate("payment_date").toLocalDate())
                .amount(resultSet.getInt("amount"))
                .build();
    }

    public static Port mapResultSetToPort(ResultSet resultSet) throws SQLException {
        return Port.newBuilder()
                .id(resultSet.getInt("port_id"))
                .portNameUa(resultSet.getString("port_name_ua"))
                .portNameEn(resultSet.getString("port_name_en"))
                .countryUa(resultSet.getString("country_ua"))
                .countryEn(resultSet.getString("country_en"))
                .build();
    }

    public static RoutePoint mapResultSetToRoutePoint(ResultSet resultSet) throws SQLException {
        return RoutePoint.newBuilder()
                .id(resultSet.getInt("route_point_id"))
                .route(mapResultSetToRoute(resultSet))
                .dayNumber(resultSet.getInt("day_number"))
                .port(mapResultSetToPort(resultSet))
                .build();
    }
}