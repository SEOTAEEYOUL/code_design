package com.code.design.doamin.member.application;


import com.code.design.doamin.member.dao.MemberRepository;
import com.code.design.doamin.member.dao.MemberSignUpRequest;
import com.code.design.doamin.member.domain.Member;
import com.code.design.member.MemberSignedUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 서비스 클래스를 만들 때 행위 중심으로 만들어야 한다.
 * 1. 회원가입에 대한 책임을 갖는다.
 *
 * MemberService -> Member All ...
 */
@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    // 비밀번호 변경하는 로직을 생성 X
    // 이메일 업데이트 가능 X

    private final MemberRepository memberRepository;
    private final com.code.design.coupon.CouponIssueService couponIssueService;
    //    private final EmailSenderService emailSenderService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void signUp(final MemberSignUpRequest dto) {
        final Member member = memberRepository.save(dto.toEntity()); // 1. member 엔티티 영속화
//        emailSenderService.sendSignUpEmail(member); // 2. 외부 시스템 이메일 호출
        eventPublisher.publishEvent(new MemberSignedUpEvent(member));
        couponIssueService.issueSignUpCoupon(member.getId()); // 3. 회원가입 쿠폰 발급 -> 예외 발생, 회원, 쿠폰 모두 롤백, 문제는 회원 가입 이메일 전송 완료...

    }
}
