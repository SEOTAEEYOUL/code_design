package com.code.design;

import com.code.design.doamin.member.domain.Member;
import com.code.design.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            // throw new IllegalArgumentException("System Error Java 8 version ....");
            throw new EntityNotFoundException(ErrorCode.INVALID_INPUT_VALUE);
            // throw new EntityNotFoundException(ErrorCode.INVALID_INPUT_VALUE.getMessage());
        }
        else if (value.equals("none")) {
            throw new IllegalStateException("System Error Java 8 version .... none");
        }
        else {
            return new Member("yun", "yun@asd.com");

        }
    }

    @PostMapping
    public void test2(
            @RequestBody @Valid Member member
            ) {
    }
}
