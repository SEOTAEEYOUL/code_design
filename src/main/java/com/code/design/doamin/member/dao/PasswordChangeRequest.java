package com.code.design.doamin.member.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordChangeRequest {

    private String authCode;
    private String password;
    private String newPassword;
}
