package com.code.design.doamin.order.order.dto;

import java.math.BigDecimal;

import com.code.design.doamin.order.order.exception.OrderSheetForm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@OrderSheetForm
public class OrderSheetRequest {

    @Min(1)
    private BigDecimal price;

    @NotNull
    @Valid
    private Payment payment;

    @NotNull
    @Valid
    private Address address;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class Payment {

        @NotNull
        private PaymentMethod paymentMethod;
        private Account account;
        private Card card;


    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class Address {

        private String city;
        private String state;
        private String zipCode;
    }


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class Account {

        private String number;
        private String bankCode;
        private String holder;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class Card {

        private String number;
        private String brand;
        private String csv;
    }
}