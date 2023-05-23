package com.code.design.order;

import java.math.BigDecimal;
import java.util.Set;

import com.code.design.doamin.order.order.domain.MessageType;
import com.code.design.doamin.order.order.domain.Order;
import com.code.design.doamin.order.order.domain.Orderer;
import lombok.Getter;

@Getter
public class OrderRequest {

    private BigDecimal productAmount;
    private Long productId;
    private Orderer orderer;

    // @NotNull
    private Set<MessageType> messageType;

    public Order toEntity() {
        return new Order(productId, productAmount, orderer);
    }
}
