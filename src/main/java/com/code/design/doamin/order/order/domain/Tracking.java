package com.code.design.doamin.order.order.domain;


import com.code.design.doamin.model.Address;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Tracking {
    private Address address;
}
