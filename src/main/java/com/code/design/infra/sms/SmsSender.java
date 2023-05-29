package com.code.design.infra.sms;

import com.code.design.infra.sms.dto.SmsMessageRequest;

public interface SmsSender {
    boolean send(final SmsMessageRequest smsMessageRequest);
}
