package com.code.design.doamin.member.dao;

public interface ChangePasswordService {
    public void change(Long id, PasswordChangeRequest dto);
}
