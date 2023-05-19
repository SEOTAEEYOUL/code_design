package com.code.design.doamin.member.api;

import com.code.design.validation.EmailUnique;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @EmailUnique
    @Email
    private String email;

    public SignUpRequest(String email) {
        this.email = email;
    }
}