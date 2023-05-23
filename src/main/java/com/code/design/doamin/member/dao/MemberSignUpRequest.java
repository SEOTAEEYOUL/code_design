package com.code.design.doamin.member.dao;

import com.code.design.doamin.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String name;
    private String email;

    public Member toEntity() {
        return new Member(name, email);
    }
}
