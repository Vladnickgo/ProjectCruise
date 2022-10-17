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

import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);


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
    public List<PaymentResponseDto> findPaymentsByPaymentRequestDto(Integer userId) {
        return paymentRepository.findPaymentByUserId(userId).stream()
                .map(this::paymentToPaymentResponseDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfPages(PaymentRequestDto paymentRequestDto) {
        return paymentRepository.countAll(paymentRequestDto);
    }

    @Override
    public List<PaymentResponseDto> findPaymentsByPaymentRequestDto(PaymentRequestDto paymentRequestDto) {
        Integer firstRecordOnPage = getFirstRecordOnPage(paymentRequestDto);
        return paymentRepository.findPaymentsByUserIdAndSortingParameters(paymentRequestDto, firstRecordOnPage)
                .stream()
                .map(this::paymentToPaymentResponseDtoMapper)
                .collect(Collectors.toList());
    }


    @NotNull
    private PaymentResponseDto paymentToPaymentResponseDtoMapper(Payment payment) {
        return PaymentResponseDto.newBuilder()
                .firstName(payment.getOrder().getUser().getFirstName())
                .lastName(payment.getOrder().getUser().getLastName())
                .email(payment.getOrder().getUser().getEmail())
                .paymentNumber(payment.getId())
                .paymentDate(payment.getPaymentDate())
                .cruiseName(payment.getOrder().getCruise().getCruiseName())
                .routeName(payment.getOrder().getCruise().getRoute().getRouteName())
                .cabinNumber(payment.getOrder().getCabinStatus().getCabin().getId())
                .cabinType(payment.getOrder().getCabinStatus().getCabin().getCabinType().getCabinTypeName())
                .dateStart(payment.getOrder().getCruise().getDateStart())
                .dateEnd(payment.getOrder().getCruise().getDateEnd())
                .amount(payment.getAmount())
                .build();
    }

    private Integer getFirstRecordOnPage(PaymentRequestDto paymentRequestDto) {
        PaymentRequestDtoUtil paymentRequestDtoUtil = new PaymentRequestDtoUtil(paymentRequestDto);
        return paymentRequestDtoUtil.getItemsOnPage() * (paymentRequestDtoUtil.getNumberOfPage() - 1);
    }

}
