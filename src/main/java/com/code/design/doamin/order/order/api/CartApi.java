package com.code.design.cart;

import java.util.List;

import com.code.design.doamin.order.order.dao.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.code.design.doamin.order.order.domain.Cart;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartApi {

    private final CartRepository cartRepository;

    @GetMapping
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }
}
