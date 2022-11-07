package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.dao.entity.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentMapperTest {
    private final Mapper<PaymentDto, Payment> paymentMapper = new PaymentMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForMapEntityToPaymentDtoMethod")
    void checkMapEntityToDto(Payment payment, PaymentDto expectedPaymentDto, String message) {
        PaymentDto actualPaymentDto = paymentMapper.mapEntityToDto(payment);
        assertEquals(expectedPaymentDto, actualPaymentDto, message);
    }

    private static Stream<Arguments> provideDataForMapEntityToPaymentDtoMethod() {
        return Stream.of(Arguments.of(Payment.newBuilder()
                        .id(1)
                        .amount(1000)
                        .paymentDate(LocalDate.parse("2022-11-01"))
                        .order(Order.newBuilder()
                                .id(1)
                                .user(User.newBuilder()
                                        .id(5)
                                        .firstName("Bob")
                                        .lastName("Martin")
                                        .email("martyn@gmail.com")
                                        .role(Role.USER)
                                        .password("e5c22a6d35adcf0f962be188349080ea60be9d5c")
                                        .salt("37a16e31dced8345")
                                        .build())
                                .userDocuments("doc_img.jpg")
                                .orderDate(LocalDate.parse("2022-11-01"))
                                .cruise(Cruise.newBuilder()
                                        .id(1)
                                        .ship(Ship.newBuilder()
                                                .id(1)
                                                .shipName("Silver Wave")
                                                .passengersCapacity(1000)
                                                .numberOfStaff(200)
                                                .shipImageSource("img.jpg")
                                                .build())
                                        .build())
                                .orderStatus(OrderStatus.newBuilder()
                                        .id(1)
                                        .orderStatusName("Order status")
                                        .build())
                                .cabinStatus(CabinStatus.newBuilder()
                                        .id(1)
                                        .statusStatement(CabinStatusStatement.newBuilder()
                                                .id(1)
                                                .statusStatementName("available")
                                                .build())
                                        .statusStart(LocalDate.parse("2022-10-01"))
                                        .statusEnd(LocalDate.parse("2023-10-01"))
                                        .cabin(Cabin.newBuilder()
                                                .id(1)
                                                .ship(Ship.newBuilder()
                                                        .id(1)
                                                        .shipName("Silver Wave")
                                                        .passengersCapacity(1000)
                                                        .numberOfStaff(200)
                                                        .shipImageSource("img.jpg")
                                                        .build())
                                                .cabinType(CabinType.newBuilder()
                                                        .id(1)
                                                        .cabinTypeName("single")
                                                        .numberOfBeds(1)
                                                        .price(500)
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build(),
                PaymentDto.newBuilder()
                        .id(1)
                        .paymentDate(LocalDate.parse("2022-11-01"))
                        .amount(1000)
                        .orderDto(OrderDto.newBuilder()
                                .id(1)
                                .userId(5)
                                .userDocuments("doc_img.jpg")
                                .cabinStatusId(1)
                                .orderDate(LocalDate.parse("2022-11-01"))
                                .orderStatusId(1)
                                .cruiseId(1)
                                .build())
                        .build(), "Check mapEntityToPaymentDto method"));
    }
}