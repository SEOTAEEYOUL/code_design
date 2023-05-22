package com.code.design.doamin.member.domain;

import com.code.design.lombok.Coupon;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;



//    @OneToMany
//    @JoinColumn(name = "coupon_id")
//    // 순환 참조가 되고 있는 부분을 제외 시킨
//    @ToString.Exclude
//    private List<Coupon> coupons = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;


    @Builder
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.createAt = LocalDateTime.now( );
    }

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.createAt = LocalDateTime.now( );
    }

    public void changePassword(String password){
        this.password = password;
    }

}
