package com.code.design.doamin.order.cart.dao;

import com.code.design.doamin.order.cart.domain.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteByProductId(Long productId);
}
