package com.code.design.order;

import com.code.design.doamin.order.cart.application.CartService;
import com.code.design.doamin.order.order.domain.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final CartService cartService;

    @Async
    @EventListener
    public void orderCompletedEventListener(OrderCompletedEvent event) {
        cartService.deleteCart(event.getOrder());
    }

}