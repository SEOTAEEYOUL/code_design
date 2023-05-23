package com.code.design.doamin.order.order.dao;

import com.code.design.doamin.order.order.domain.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteByProductId(Long productId);
}
