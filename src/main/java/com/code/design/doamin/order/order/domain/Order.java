package com.code.design.doamin.order.order.domain;


import com.code.design.doamin.model.Address;
import com.code.design.doamin.order.item.domain.OrderItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
