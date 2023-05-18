package com.code.design.doamin.order.order.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Orderer {
    private Long id;
    private String name;
}
