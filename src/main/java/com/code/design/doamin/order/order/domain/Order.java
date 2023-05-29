package com.code.design.doamin.order.order.domain;


import com.code.design.doamin.model.Address;
import com.code.design.doamin.order.item.domain.OrderItem;
// import com.mysema.commons.lang.Assert;
import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(force=true) // 아무 인수가 없는 생성자를 생성, 변수를 초기값으로 설정
public class Order {

    @Id
    // 읽기 전용 속성 추가로 중복된 매핑 오류가 해결
    // @Column(insertable=false, updatable=false)
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    // @Transient
    @Column(name = "product_id", nullable = false)
    // 읽기 전용 속성을 설정하여 중복된 매핑 오류 해결
    // @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Long productId;


    @Column(name = "product_amount", nullable = false)
    // 읽기 전용 속성을 설정하여 중복된 매핑 오류 해결
    // @Column(name = "product_amount", nullable = false, insertable = false, referencedColumnName="id", updatable = false)
    private BigDecimal productAmount;


    @ElementCollection
    @CollectionTable(name = "orders_item", joinColumns = @JoinColumn(name = "id", insertable = false, updatable = false))
    // @Column(insertable=false, updatable=false) // 값이 하나고 내가 정의한 것이 아니기 때문에 예외적으로 컬럼명 변경 허용
    @Embedded
    final private List<OrderItem> orderItemList = new ArrayList<>();


    @Embedded
    private Orderer orderer;

    @Embedded
    private Address address;

    @Embedded
    private OrderMessage message;


    @Builder
    public Order(OrderMessage orderMessage) {
        this.message = orderMessage;
    }

    @Builder
    public Order(Long productId, BigDecimal productAmount, Orderer orderer) {
        this.productId     = productId;
        this.productAmount = productAmount;
        this.orderer       = orderer;
    }

    @Builder
    public Order(Address address) {
        // Assert.notNull(address, "address must not be null");

        this.address = address;
    }
}
