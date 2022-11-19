package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.PaymentDto;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;

public class PaymentValidator implements Validator<PaymentDto> {


    @Override
    public void validate(PaymentDto entity) {
        if (entity == null) {
            throw new IllegalArgumentException(PAYMENT_IS_NULL);
        }
        if(entity.equals(PaymentDto.newBuilder().build())){
            throw new IllegalArgumentException(PAYMENT_IS_EMPTY);
        }
        if(entity.getAmount()==null){
            throw new IllegalArgumentException(AMOUNT_IS_NULL);
        }
        if(entity.getPaymentDate()==null){
            throw new IllegalArgumentException(PAYMENT_DATE_IS_NULL);
        }
        if(entity.getOrderDto()==null){
            throw new IllegalArgumentException(ORDER_IS_NULL);
        }
    }
}
