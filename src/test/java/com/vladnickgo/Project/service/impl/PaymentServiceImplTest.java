package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.OrderDto;
import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.dao.PaymentDao;
import com.vladnickgo.Project.dao.entity.*;
import com.vladnickgo.Project.service.mapper.PaymentMapper;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;
import com.vladnickgo.Project.validator.PaymentValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    private static final Integer PAYMENT_STATUS_CANCELED = 2;
    private static final Integer ORDER_STATUS_CANCELED = 3;
    private static final PaymentDto TEST_PAYMENT_DTO = PaymentDto.newBuilder()
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
            .build();
    private static final Payment TEST_PAYMENT_ENTITY = Payment.newBuilder()
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
                            .cruiseName("Winter Route")
                            .dateStart(LocalDate.parse("2022-12-01"))
                            .dateEnd(LocalDate.parse("2022-12-11"))
                            .nights(10)
                            .route(Route.newBuilder()
                                    .id(1)
                                    .routeName("europe")
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
            .build();

    private static final PaymentResponseDto TEST_PAYMENT_RESPONSE_DTO = PaymentResponseDto.newBuilder()
            .paymentNumber(1)
            .paymentDate(LocalDate.parse("2022-11-01"))
            .firstName("Bob")
            .lastName("Martin")
            .email("martyn@gmail.com")
            .userDocument("doc_img.jpg")
            .cruiseName("Winter Route")
            .routeName("europe")
            .cabinNumber(1)
            .cabinType("single")
            .dateStart(LocalDate.parse("2022-12-01"))
            .dateEnd(LocalDate.parse("2022-12-11"))
            .orderStatusName("Order status")
            .amount(1000)
            .build();

    private static final Payment TEST_REQUEST_PAYMENT_ENTITY = Payment.newBuilder()
            .id(1)
            .amount(1000)
            .paymentDate(LocalDate.parse("2022-11-01"))
            .order(Order.newBuilder()
                    .id(1)
                    .user(User.newBuilder()
                            .id(5)
                            .build())
                    .userDocuments("doc_img.jpg")
                    .orderDate(LocalDate.parse("2022-11-01"))
                    .cruise(Cruise.newBuilder()
                            .id(1)
                            .build())
                    .orderStatus(OrderStatus.newBuilder()
                            .id(1)
                            .build())
                    .cabinStatus(CabinStatus.newBuilder()
                            .id(1)
                            .statusStatement(CabinStatusStatement.newBuilder()
                                    .id(1)
                                    .build())
                            .build())
                    .build())
            .build();

    List<Payment> TEST_PAYMENT_LIST = List.of(TEST_PAYMENT_ENTITY);
    List<PaymentResponseDto> TEST_PAYMENT_RESPONSE_DTO_LIST = List.of(TEST_PAYMENT_RESPONSE_DTO);
    @Mock
    private PaymentDao paymentRepository;

    @Mock
    PaymentRequestDtoUtil paymentRequestDtoUtil;

    @Spy
    private PaymentValidator validator;

    @Spy
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckAddPaymentWithNull")
    public void addPaymentWithNullParameters(PaymentDto paymentDto, String expected, String message) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> paymentService.addPayment(paymentDto));
        assertEquals(expected, illegalArgumentException.getMessage(), message);
    }

    @Test
    public void addPaymentIsSuccessful() {
        when(paymentMapper.mapDtoToEntity(TEST_PAYMENT_DTO)).thenReturn(TEST_REQUEST_PAYMENT_ENTITY);
        when(paymentRepository.addPayment(TEST_REQUEST_PAYMENT_ENTITY)).thenReturn(TEST_PAYMENT_ENTITY);
        PaymentResponseDto actual = paymentService.addPayment(TEST_PAYMENT_DTO);
        assertEquals(TEST_PAYMENT_RESPONSE_DTO, actual);
    }

    @Test
    public void findPaymentsByUserIdAndSortingParametersIsSuccessful() {
        when(paymentRepository.findPaymentsByUserIdAndFilterParameters(paymentRequestDtoUtil, 0)).thenReturn(TEST_PAYMENT_LIST);
        when(paymentRequestDtoUtil.extractedComparator()).thenReturn(Comparator.comparing(PaymentResponseDto::getPaymentDate));
        List<PaymentResponseDto> actual = paymentService.findPaymentsByUserIdAndSortingParameters(paymentRequestDtoUtil);
        assertEquals(TEST_PAYMENT_RESPONSE_DTO_LIST, actual);
    }

    @Test
    public void findPaymentsByFilterParametersIsSuccessful() {
        when(paymentRepository.findPaymentsByFilterParameters(paymentRequestDtoUtil, 0)).thenReturn(TEST_PAYMENT_LIST);
        List<PaymentResponseDto> actual = paymentService.findPaymentsByFilterParameters(paymentRequestDtoUtil);
        assertEquals(TEST_PAYMENT_RESPONSE_DTO_LIST, actual);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    public void getNumberOfPagesByFilterParametersTest(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        when(paymentRepository.countAllPaymentsByFilterParameters(paymentRequestDtoUtil)).thenReturn(totalRecords);
        when(paymentRequestDtoUtil.getItemsOnPage()).thenReturn(recordsOnPage);
        Integer actual = paymentService.getNumberOfPagesByFilterParameters(paymentRequestDtoUtil);
        assertEquals(expectedPages, actual, message);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideNumberOfRecordsOnPageAndTotalRecords")
    public void getNumberOfPagesByUserIdAndSortingParametersTest(Integer recordsOnPage, Integer totalRecords, Integer expectedPages, String message) {
        when(paymentRepository.countAllPaymentsByUserIdAndFilterParameters(paymentRequestDtoUtil)).thenReturn(totalRecords);
        when(paymentRequestDtoUtil.getItemsOnPage()).thenReturn(recordsOnPage);
        Integer actual = paymentService.getNumberOfPagesByUserIdAndSortingParameters(paymentRequestDtoUtil);
        assertEquals(expectedPages, actual, message);
    }

    @Test
    public void cancelPayment() throws SQLException {
        paymentService.cancelPayment(TEST_PAYMENT_DTO.getId());
        verify(paymentRepository).updatePaymentStatusByPaymentId(TEST_PAYMENT_DTO.getId(), PAYMENT_STATUS_CANCELED, ORDER_STATUS_CANCELED);
    }

    private static Stream<Arguments> provideDataForCheckAddPaymentWithNull() {
        return Stream.of(
                Arguments.of(PaymentDto.newBuilder().build(),
                        "Payment is empty",
                        "Check addPayment with empty paymentDto"),
                Arguments.of(PaymentDto.newBuilder()
                                .paymentDate(LocalDate.parse("2022-11-17"))
                                .amount(1200)
                                .build(),
                        "Order is null",
                        "Check addPayment with empty orderDto"),
                Arguments.of(PaymentDto.newBuilder()
                                .orderDto(OrderDto.newBuilder().build())
                                .amount(1200)
                                .build(),
                        "PaymentDate is null",
                        "Check addPayment with empty paymentDate"),
                Arguments.of(PaymentDto.newBuilder()
                                .orderDto(OrderDto.newBuilder().build())
                                .paymentDate(LocalDate.parse("2022-11-17"))
                                .build(),
                        "Amount is null",
                        "Check addPayment with empty amount"),
                Arguments.of(null,
                        "Payment is null",
                        "Check addPayment with null payment")
        );
    }

    private static Stream<Arguments> provideNumberOfRecordsOnPageAndTotalRecords() {
        return Stream.of(
                Arguments.of(
                        5, 7, 2, "Check for 7 records, 5 records on page"
                ),
                Arguments.of(
                        5, 0, 1, "Check for 0 records, 5 records on page"
                ),
                Arguments.of(
                        5, 10, 2, "Check for 11 records, 10 records on page"
                ),
                Arguments.of(
                        15, 15, 1, "Check for 7 records, 5 records on page"
                )
        );
    }
}