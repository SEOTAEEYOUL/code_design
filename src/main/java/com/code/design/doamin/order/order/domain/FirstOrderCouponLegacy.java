package com.code.design.doamin.order.order.domain;

import com.code.design.doamin.coupon.domain.CouponLegacy;

import java.time.LocalDate;

/**
 * 1. 첫 쿠폰
 * 2. 생일 쿠폰 -> 중복으로 ...
 */
public class FirstOrderCouponLegacy {

    /**
     * 안티 패턴
     *
     * 꼬치꼬치 캐묻고 있습니다.
     *
     * 1. 개체간의 협력관계에서는 상대 객체에 대한 정보를 꼬치꼬치 묻지 않아야합니다. 묻지말고 시켜라 ->
     */
    public void apply(final long couponId) {

        if (canIssued()) {
            final CouponLegacy coupon = getCoupon(couponId);

            // Coupon 개체에서 할일
            if (LocalDate.now().isAfter(coupon.getExpirationDate())) {
                throw new IllegalStateException("사용 기간이 만료된 쿠폰입니다.");
            }

            // Coupon 개체에서 할일
            if (coupon.isUsed()) {
                throw new IllegalStateException("이미 사용한 쿠폰입니다.");
            }
        }
    }

    // 실제는 데이터베이스 조회..
    private CouponLegacy getCoupon(final Long id) {
        return new CouponLegacy(1000, LocalDate.now().plusDays(3));
    }

    private boolean canIssued() {
        // TODO: 첫 구매인지 확인 하는 로직 ...
        return true;
    }
}