package com.code.design.coupon;


import com.code.design.doamin.order.order.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

interface CouponRepository extends JpaRepository<Coupon, Long> {

}
