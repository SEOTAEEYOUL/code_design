package com.code.design.global.error.exception;

// import com.code.design.domain.member.dao.MemberRepository;
import java.text.MessageFormat;

import com.code.design.doamin.member.dao.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<EmailUnique, String> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(EmailUnique emailUnique) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {

        boolean isExistEmail = memberRepository.existsByEmail(email);

        if (isExistEmail) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(
                            // MessageFormat.format("Email {0} already exists!", email))
                            MessageFormat.format("Email {0} 이미 존재하는 이메일 입니다!", email))
                    .addConstraintViolation();
        }
        return !isExistEmail;
    }
}