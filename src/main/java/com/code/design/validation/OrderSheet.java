package com.code.design.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailDuplicationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderSheet {
    String message( ) default "주문 정보가 올바르지 않습니다.";

    Class<?>[] groups( ) default {};

    Class<? extends Payload>[] payload( ) default {};
}
