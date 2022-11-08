package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.dao.entity.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {
    private final Mapper<OrderDto, Order> orderMapper = new OrderMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapEntityToOrderDtoMethod")
    void mapEntityToDto(Order order, OrderDto expectedOrderDto, String message) {
        OrderDto actualOrderDto = orderMapper.mapEntityToDto(order);
        assertEquals(expectedOrderDto, actualOrderDto, message);
    }

    private static Stream<Arguments> provideDataForMapEntityToOrderDtoMethod() {
        return Stream.of(
                Arguments.of(
                        Order.newBuilder()
                                .id(1)
                                .user(User.newBuilder()
                                        .id(5)
                                        .firstName("Bob")
                                        .lastName("Martyn")
                                        .email("martyn@mail.com")
                                        .password("e5c22a6d35adcf0f962be188349080ea60be9d5c")
                                        .salt("37a16e31dced8345")
                                        .role(Role.USER)
                                        .build())
                                .cabinStatus(CabinStatus.newBuilder()
                                        .id(1)
                                        .cabin(Cabin.newBuilder()
                                                .id(1)
                                                .cabinType(CabinType.newBuilder()
                                                        .id(1)
                                                        .numberOfBeds(2)
                                                        .cabinTypeName("balcony")
                                                        .price(1200)
                                                        .build())
                                                .build())
                                        .statusStart(LocalDate.parse("2022-10-01"))
                                        .statusEnd(LocalDate.parse("2023-10-01"))
                                        .statusStatement(CabinStatusStatement.newBuilder()
                                                .id(1)
                                                .statusStatementName("available")
                                                .build())
                                        .build())
                                .orderDate(LocalDate.parse("2022-11-08"))
                                .orderStatus(OrderStatus.newBuilder()
                                        .id(1)
                                        .orderStatusName("confirmed")
                                        .build())
                                .cruise(Cruise.newBuilder()
                                        .id(1)
                                        .ship(Ship.newBuilder()
                                                .id(1)
                                                .shipName("Silver Wave")
                                                .shipImageSource("ship_img.jpg")
                                                .numberOfStaff(1200)
                                                .passengersCapacity(2000)
                                                .build())
                                        .nights(8)
                                        .dateStart(LocalDate.parse("2022-11-10"))
                                        .dateEnd(LocalDate.parse("2022-11-19"))
                                        .cruiseStatus(CruiseStatus.newBuilder()
                                                .id(1)
                                                .cruiseStatusName("available")
                                                .build())
                                        .cruiseName("Golden tour")
                                        .route(Route.newBuilder()
                                                .id(1)
                                                .routeName("european")
                                                .build())
                                        .build())
                                .userDocuments("user_doc.jpg")
                                .build(),
                        OrderDto.newBuilder()
                                .id(1)
                                .userId(5)
                                .userDocuments("user_doc.jpg")
                                .cabinStatusId(1)
                                .orderDate(LocalDate.parse("2022-11-08"))
                                .orderStatusId(1)
                                .cruiseId(1)
                                .build(),
                        ""));
    }
}