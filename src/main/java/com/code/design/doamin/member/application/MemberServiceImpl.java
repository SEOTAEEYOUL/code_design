package com.code.design.doamin.member.application;

import com.code.design.doamin.member.application.MemberService;
import com.code.design.doamin.member.dao.PasswordChangeRequest;
import com.code.design.doamin.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Override
    public Member findById(Long id) {
        // 로직 구현...
        return null;
    }

    @Override
    public Member findByEmail(String email) {
        // 로직 구현...
        return null;
    }

    @Override
    public Member create(Member member) {
        // 로직 구현...
        return null;
    }

    @Override
    public void changePassword(PasswordChangeRequest dto) {
        // 로직 구현...
    }

    @Override
    public Member updateName(Long id, String name) {
        // 로직 구현...
        return null;
    }
}