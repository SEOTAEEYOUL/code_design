package com.code.design.infra.sms;

import com.code.design.infra.sms.dto.SmsMessageRequest;
import com.code.design.infra.sms.AmazonSmsClient.SendRequest;
import com.code.design.infra.sms.AmazonSmsClient.SendResponse;

public class AmazonSmsSenderService implements SmsSender {

    @Override
    public boolean send(final SmsMessageRequest dto) {
        final SendResponse response = new AmazonSmsClient().send(
                new SendRequest(dto.getMessage( ), dto.getReceiverNumber(), dto.getItc())
        );

        return response.status.equals("SUCCESS");
    }
}
