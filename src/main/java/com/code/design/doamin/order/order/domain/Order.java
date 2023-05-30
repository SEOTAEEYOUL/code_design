package com.code.design.doamin.order.order.domain;

import java.math.BigDecimal;

import com.code.design.doamin.model.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_amount", nullable = false)
    private BigDecimal productAmount;


    @Embedded
    private Orderer orderer;

    @Embedded
    private Address address;

   @Embedded
   private OrderMessage message;


   public Order(OrderMessage orderMessage) {
       this.message = orderMessage;
   }


    public Order(Long productId, BigDecimal productAmount, Orderer orderer) {
        this.productId = productId;
        this.productAmount = productAmount;
        this.orderer = orderer;
    }

    @Builder
    public Order(Address address) {
        // Assert.notNull(address, "address must not be null");

        this.address = address;
    }
}


