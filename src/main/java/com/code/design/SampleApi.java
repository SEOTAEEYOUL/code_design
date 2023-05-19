package com.code.design;

import com.code.design.doamin.member.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleApi {
    @GetMapping
    public Member test(
            @RequestParam String value
    ) {
        if (value.equals("value")) {
            throw new IllegalArgumentException( );
        }
        else if (value.equals("none")) {
            throw new IllegalStateException( );
        }
        else {
            return new Member("yun", "yun@asd.com");

        }
    }

//    public static class Member {
//        private String name;
//        private String email;
//
//        public Member(String name, String email) {
//            this.name  = name;
//            this.email = email;
//        }
//    }
}
