package com.code.design.doamin.member.application;

import com.code.design.doamin.member.dao.PasswordChangeRequest;
import com.code.design.doamin.member.domain.Member;


/**
 * 1 대체성이 없다면 굳이 인터페이스가 필요 없다
 *
 */
public interface MemberService {

    Member findById(Long id);

    Member findByEmail(String email);

    Member create(Member member);

    // 1. 비밀 번호 기반으로 비밀번호 변경 -> 인증 수단
    // 2. 비밀 번호 찾기 -> 인증 수단 -> 비밀번호 X, 이메일 인증, SMS 인증
    void changePassword(PasswordChangeRequest dto);

    Member updateName(Long id, String name);

}