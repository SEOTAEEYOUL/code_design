package com.code.design.validation;

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
                            MessageFormat.format("Email {0} already exists!", email))
                    .addConstraintViolation();
        }
        return !isExistEmail;
    }
}