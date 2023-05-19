package com.code.design;

import com.code.design.doamin.member.dao.MemberRepository;
import com.code.design.doamin.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.save(
                new Member("yun","yun@yun.com")
        );
        memberRepository.save(
                new Member("test","test@test.com")
        );
        memberRepository.save(
                new Member("seo","seo@seo.com")
        );
    }
}
