package com.code.design.member;

import com.code.design.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MemberEventHandler {

    private final EmailSenderService emailSenderService;

//    @EventListener
    @TransactionalEventListener
    public void memberSignedUpEventListener(MemberSignedUpEvent dto){
        emailSenderService.sendSignUpEmail(dto.getMember());
    }
}
