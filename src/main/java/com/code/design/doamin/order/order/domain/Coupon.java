package com.code.design.doamin.order.order.domain;


import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
// @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    private long id;

    private boolean used;

    private double amount;

    private LocalDate expirationDate;

    public Coupon(double amount, LocalDate expirationDate) {
        this.amount         = amount;
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