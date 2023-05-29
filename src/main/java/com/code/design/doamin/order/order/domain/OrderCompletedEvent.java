package com.code.design.doamin.order.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderCompletedEvent {

    private final Order order;

}
