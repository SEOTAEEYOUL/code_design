package com.code.design.doamin.order.order.dao;

import com.code.design.doamin.order.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
