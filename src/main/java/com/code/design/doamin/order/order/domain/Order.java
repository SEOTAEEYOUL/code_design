package com.code.design.doamin.order.order.domain;


import com.code.design.doamin.model.Address;
import com.code.design.doamin.order.item.domain.OrderItem;
import com.code.design.doamin.order.order.dto.OrderMessage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "orders_item", joinColumns = @JoinColumn(name = "id"))
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Embedded
    private Address address;

    private OrderMessage message;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_amount", nullable = false)
    private BigDecimal productAmount;

    @Embedded
    private Orderer orderer;

    public Order(OrderMessage orderMessage) {
        this.message = orderMessage;
    }

    public Order(Long productId, BigDecimal productAmount, Orderer orderer) {
        this.productId = productId;
        this.productAmount = productAmount;
        this.orderer = orderer;
    }
}
