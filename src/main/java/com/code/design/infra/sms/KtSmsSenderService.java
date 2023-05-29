package com.code.design.infra.sms;


import com.code.design.infra.sms.dto.SmsMessageRequest;
import com.code.design.infra.sms.KtSmsSenderClient.SendRequest;
import com.code.design.infra.sms.KtSmsSenderClient.SendResponse;

public class KtSmsSenderService implements SmsSender {
    @Override
    public boolean send(final SmsMessageRequest dto) {
        final KtSmsSenderClient client = new KtSmsSenderClient();
        final SendResponse response = client.send(new SendRequest(dto.getMessage(), dto.getReceiverNumber()));
        return response.success;
    }
}
