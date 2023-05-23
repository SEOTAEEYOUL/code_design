package com.code.design.order;

import com.code.design.doamin.order.order.domain.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderCompletedEvent {

    private final Order order;

}
