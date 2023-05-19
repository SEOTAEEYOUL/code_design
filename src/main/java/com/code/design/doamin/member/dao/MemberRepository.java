package com.code.design.doamin.member.dao;

import com.code.design.doamin.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    public Member findByEmail(String email);

}