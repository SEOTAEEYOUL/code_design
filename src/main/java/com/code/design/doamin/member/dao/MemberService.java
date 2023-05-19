package com.code.design.doamin.member.dao;

import com.code.design.doamin.member.domain.Member;

public interface MemberService {

    Member findById(Long id);

    Member findByEmail(String email);

    Member create(Member member);

    void changePassword(PasswordChangeRequest dto);

    Member updateName(Long id, String name);

}