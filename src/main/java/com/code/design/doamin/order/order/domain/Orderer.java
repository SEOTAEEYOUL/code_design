package com.code.design.doamin.order.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Orderer {
    private Long id;
    private String name;

    @Column(name = "member_id", nullable = false, updatable = false)
    private Long memberId;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;
}
