package com.code.design.lombok;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.ToString.*;

@Entity
@Table(name = "member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "coupon_id")
    // 순환 참조가 되고 있는 부분을 제외 시킨
    @Exclude
    private List<Coupon> coupons = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}
