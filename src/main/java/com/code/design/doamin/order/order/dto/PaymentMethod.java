package com.code.design.doamin.order.order.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public
enum PaymentMethod {

    CARD("카드"),
    BANK_TRANSFER("무통장 입금");

    private final String description;
}