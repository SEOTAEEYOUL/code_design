package com.code.design.doamin.order.order.domain;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "coupon")
@Getter
// @Setter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean used;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "member_id", nullable = false, updatable = false)
    private Long memberId;

    private LocalDate expirationDate;


    public Coupon(BigDecimal amount, Long memberId) {
        this.amount = amount;
        this.memberId = memberId;
    }

    public Coupon(BigDecimal amount, Long memberId,  LocalDate expirationDate) {
        this.amount         = amount;
        this.memberId       = memberId;
        this.expirationDate = expirationDate;
        this.used           = false;
    }

    // 적용
    // 1. 만료 여부
    // 2. 사용 여부
    // 3. 사용 설정
    public void apply() {

        verifyCouponIsAvailable();
        this.used = true;        
    }

    private void verifyCouponIsAvailable() {
        // if (isExpiration()) {
        //     thow new IllegalArgumentException("만료된 쿠폰 입니다.");
        // }

        // if (this.used) {
        //     throw new IllegalArgumentException("이미 사용한 쿠폰입니다");
        // }
        
        verifyExpiration();
        verifyUsed();
    }

    private boolean isExpiration() {
        return LocalDate.now().isAfter(expirationDate);
    }

    
    // 만료 여부
    private void verifyExpiration() {
        if (isExpiration()) {
            throw new IllegalArgumentException("만료된 쿠폰입니다.");
        }
    }


    // 사용 여부
    private void verifyUsed() {
        if (this.used) {
            throw new IllegalArgumentException("이미 사용한 쿠폰입니다.");
        }
    }
}