package com.code.design.doamin.member.api;

import com.code.design.doamin.member.dao.MemberRepository;
import com.code.design.doamin.member.application.MemberService;
import com.code.design.doamin.member.application.MemberSignUpService;
import com.code.design.doamin.member.dao.MemberSignUpRequest;
import com.code.design.doamin.member.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;
    private final MemberSignUpService memberSignUpService;
    private final MemberRepository memberRepository;

    @GetMapping("/all")
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    // rollback 진행 O
//    @PostMapping("/unchekced")
//    public Member unchekced() {
//        final Member member = memberService.createUncheckedException();
//        return member;
//    }

    // rollback 진행 X
//    @PostMapping("/chekced")
//    public Member chekced() throws IOException {
//        final Member member = memberService.createCheckedException();
//        return member;
//    }

    @PostMapping("/create")
    public Member create(@RequestBody @Valid final SignUpRequest dto) {
        return memberRepository.save(Member.builder()
                        .name(dto.getName())
                        .email(dto.getEmail())
                        .build());
    }

    @GetMapping("/create")
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody MemberSignUpRequest dto) {
        memberSignUpService.signUp(dto);
    }

}
