package com.code.design.doamin.coupon.application;

import java.math.BigDecimal;

import com.code.design.doamin.coupon.dao.CouponRepository;
import com.code.design.doamin.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponRepository couponRepository;

    @Transactional
    public void issueSignUpCoupon(Long memberId) {
        couponRepository.save(new Coupon(BigDecimal.TEN, memberId));
//        throw new RuntimeException("RuntimeException....");
    }
}
