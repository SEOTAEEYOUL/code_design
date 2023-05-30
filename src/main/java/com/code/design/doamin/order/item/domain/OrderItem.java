package com.code.design.doamin.order.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;
}
