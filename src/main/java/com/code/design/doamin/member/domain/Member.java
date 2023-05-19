package com.code.design.doamin.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;


    @Builder
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
