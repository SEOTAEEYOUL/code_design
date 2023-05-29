package com.code.design.doamin.coupon.dao;


import com.code.design.doamin.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
