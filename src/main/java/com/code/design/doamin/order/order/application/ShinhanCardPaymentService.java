package com.code.design.doamin.order.order.application;

import com.code.design.doamin.order.order.application.CardPaymentService;

public class ShinhanCardPaymentService implements CardPaymentService {

    @Override
    public void pay() {
        // 결제를 위한 비즈니스 로직 실행....
    }

    @Override
    public void cancel( ) {
        // 결제를 취소하는 로직 실행 ...
    }
}