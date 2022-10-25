package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.PaymentDto;
import com.vladnickgo.Project.controller.dto.PaymentRequestDto;
import com.vladnickgo.Project.controller.dto.PaymentResponseDto;
import com.vladnickgo.Project.dao.PaymentDao;
import com.vladnickgo.Project.dao.entity.Payment;
import com.vladnickgo.Project.service.PaymentService;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.PaymentRequestDtoUtil;
import com.vladnickgo.Project.validator.Validator;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);
    private static final Integer PAYMENT_STATUS_CANCELED = 2;
    private static final Integer ORDER_STATUS_CANCELED = 3;


    private final PaymentDao paymentRepository;
    private final Mapper<PaymentDto, Payment> paymentMapper;
    private final Validator<PaymentDto> validator;

    public PaymentServiceImpl(PaymentDao paymentDao, Mapper<PaymentDto, Payment> paymentMapper, Validator<PaymentDto> validator) {
        this.paymentRepository = paymentDao;
        this.paymentMapper = paymentMapper;
        this.validator = validator;
    }

    @Override
    public PaymentResponseDto addPayment(PaymentDto paymentDto) {
        validator.validate(paymentDto);
        Payment requestPayment = paymentMapper.mapDtoToEntity(paymentDto);
        Payment payment = paymentRepository.addPayment(requestPayment);
        return paymentToPaymentResponseDtoMapper(payment);
    }

    @Override
    public Integer getNumberOfPagesByUserIdAndSortingParameters(PaymentRequestDtoUtil paymentRequestDtoUtil) {
        Integer countAllPayments = paymentRepository.countAllPaymentsByUserIdAndFilterParameters(paymentRequestDtoUtil);
        return getNumberOfPages(paymentRequestDtoUtil, countAllPayments);
    }

    @Override
    public Integer getNumberOfPagesByFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil) {
        Integer countAllPayments = paymentRepository.countAllPaymentsByFilterParameters(paymentRequestDtoUtil);
        return getNumberOfPages(paymentRequestDtoUtil, countAllPayments);
    }

    @Override
    public List<PaymentResponseDto> findPaymentsByUserIdAndSortingParameters(PaymentRequestDtoUtil paymentRequestDtoUtil) {
        Integer firstRecordOnPage = getFirstRecordOnPage(paymentRequestDtoUtil);
        return paymentRepository.findPaymentsByUserIdAndFilterParameters(paymentRequestDtoUtil, firstRecordOnPage)
                .stream()
                .map(this::paymentToPaymentResponseDtoMapper)
                .sorted(paymentRequestDtoUtil.extractedComparator())
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDto> findPaymentsByFilterParameters(PaymentRequestDtoUtil paymentRequestDtoUtil) {
        Integer firstRecordOnPage = getFirstRecordOnPage(paymentRequestDtoUtil);
        return paymentRepository.findPaymentsByFilterParameters(paymentRequestDtoUtil, firstRecordOnPage)
                .stream()
                .map(this::paymentToPaymentResponseDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void confirmPaymentByPaymentId(Integer paymentId) {

    }

    @Override
    public void cancelPayment(Integer paymentId) throws SQLException {
        paymentRepository.updatePaymentStatusByPaymentId(paymentId, PAYMENT_STATUS_CANCELED, ORDER_STATUS_CANCELED);
    }

    @NotNull
    private PaymentResponseDto paymentToPaymentResponseDtoMapper(Payment payment) {
        return PaymentResponseDto.newBuilder()
                .firstName(payment.getOrder().getUser().getFirstName())
                .lastName(payment.getOrder().getUser().getLastName())
                .email(payment.getOrder().getUser().getEmail())
                .userDocument(payment.getOrder().getUserDocuments())
                .paymentNumber(payment.getId())
                .paymentDate(payment.getPaymentDate())
                .cruiseName(payment.getOrder().getCruise().getCruiseName())
                .routeName(payment.getOrder().getCruise().getRoute().getRouteName())
                .cabinNumber(payment.getOrder().getCabinStatus().getCabin().getId())
                .cabinType(payment.getOrder().getCabinStatus().getCabin().getCabinType().getCabinTypeName())
                .dateStart(payment.getOrder().getCruise().getDateStart())
                .dateEnd(payment.getOrder().getCruise().getDateEnd())
                .amount(payment.getAmount())
                .orderStatusName(payment.getOrder().getOrderStatus().getOrderStatusName())
                .build();
    }

    private Integer getFirstRecordOnPage(PaymentRequestDtoUtil paymentRequestDtoUtil) {
        return paymentRequestDtoUtil.getItemsOnPage() * (paymentRequestDtoUtil.getNumberOfPage() - 1);
    }

    private int getNumberOfPages(PaymentRequestDtoUtil paymentRequestDtoUtil, Integer countAll) {
        Integer itemsOnPage = paymentRequestDtoUtil.getItemsOnPage();
        return (countAll - 1) / itemsOnPage + 1;
    }

}
