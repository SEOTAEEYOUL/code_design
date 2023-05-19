package com.code.design.doamin.member.dao;

import java.io.IOException;

//import com.code.design.doamin.member.dao.MemberRepository;
import com.code.design.doamin.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createUncheckedException() {
        final Member member = memberRepository.save(new Member("yun", "yun@maii.com"));
        if (true) {
            throw new RuntimeException();
        }
        return member;
    }

    public Member createCheckedException() throws IOException {
        final Member member = memberRepository.save(new Member("wan", "wan@mail.com"));
        if (true) {
            throw new IOException();
        }
        return member;
    }

    public Member findById(long id) {
        return memberRepository.findById(id).get();
    }

}