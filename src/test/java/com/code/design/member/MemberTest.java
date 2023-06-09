package com.code.design.member;

import com.code.design.doamin.member.domain.Member;
import com.code.design.doamin.member.domain.Student;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    public void setter_남용의_문제() {
        // 우리는 회원에 대한 이메일 변경 기능을 제공하지 않는다.
        final Member member = new Member("name", "asd@asd.com");

//        // bean 방식
//        member.setEmail("asd@asd.com");
//        member.setName("name");
        // 객체 생성 완료
        // 앞으로 추가적인 이메일 변경은 불가능 해야한다.

        member.setEmail("new@asd.com");
    }

//    @Test
//    public void toString_양방향_순한_참조_문제() {
//        final Member member = new Member();
//        member.setEmail("asd@asd.com");
//        member.setName("name");
//
//        final Coupon coupon = new Coupon();
//        coupon.setMember(member);
//
//        final List<Coupon> coupons = new ArrayList<>();
//        coupons.add(coupon);
//        member.setCoupons(coupons);
//
//        System.out.println(member); // toString 순한 참조 발생, java.la
//    }

    @Test
    public void EqualsAndHashCode_의_문제() {
    }


    // 첫 단추 ? 첫 시작. 정상적인 객체를 생성하지 않았는데 ...? 그 뒤 로직이 의미가 있을까요 ?
    // 1. 안정성
    // 2. 객체 본인의 책임을 표현하지 못해요 -> 안전성
    // Student 객체 자신이 필수 값이라고 판단되면 반드시 객체 생성때 받아해요

    @Test
    public void 클래스_상단의_Builder의_단점( ) {
        final Student student = Student.builder()
                // .email("asd@asd..com")
                // .name("asd")
                .build();
        System.out.println(student);
    }
//    @Test
//    public void 클래스_상단의_Builder_의_문제() {
//
//        // id, createdAt, updatedAt은 데이터베이스에서 지정하기로 했는데 설정이 가능하다.
//        // email, name은 필수 값인데
//        Member.builder()
//            .id(1L)
//            .createAt(LocalDateTime.of(2021, 12, 12, 12, 12))
//            .updateAt(LocalDateTime.of(2010, 12, 12, 12, 12))
//            .build();
//
//        // 이미 사용한 쿠폰을 만들 수 있다.
//        Coupon.builder()
//            .used(false)
//            .build();
//    }

//    @Test
//    public void Builder_default_는_지양하자() {
//        final Member member = Member.builder()
//            .build();
//
//        then(member.getEmail()).isEqualTo("test@test.com"); // POJO 값 그대로 예상
//        then(member.getName()).isEqualTo("yun"); // POJO 값 그대로 예상
//    }
//
//    @Test
//    public void 생성자위_Builder_의_적적한_책임_부여() {
//        final Member member = Member.builder()
//            .name("asd")
//            .email("asd@asd.com")
//            .build();
//
////        Coupon.builder()
////            .member(member)
////            .build();
//
//    }
//
//    @Test
//    public void 생성자_접근_지시자는_최소한_으로() {
//        final Coupon coupon = new Coupon(); // 필수값, 비지니스로직을 모두 무시하고 객체 생성 가능
//        final Member member = new Member(); // 필수값, 비지니스로직을 모두 무시하고 객체 생성 가능
//    }
}