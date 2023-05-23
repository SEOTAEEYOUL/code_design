package com.code.design.member;

import com.code.design.doamin.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSignedUpEvent {

    private final Member member;
}
