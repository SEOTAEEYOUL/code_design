package com.code.design.validation;

import com.code.design.order.OrderSheetRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderSheetValidator implements ConstraintValidator<OrderSheet, OrderSheetRequest> {

    @Override
    public void initialize(OrderSheet orderSheet) {

    }

//    @Override
//    public void initialize(OrderSheet constraintAnnotation) {
//        ConstraintValidator.super.intialize(constraintAnnotation);
//    }

    @Override
    public boolean isValid(OrderSheetRequest value, ConstraintValidatorContext context) {
        return false;
    }
}
