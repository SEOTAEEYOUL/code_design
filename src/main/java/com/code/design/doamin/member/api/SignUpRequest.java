package com.code.design.doamin.member.api;

import com.code.design.global.error.exception.EmailUnique;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {



    private String name;

    @EmailUnique
    @Email
    private String email;

    public SignUpRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}