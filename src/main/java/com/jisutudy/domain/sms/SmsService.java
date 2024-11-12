package com.jisutudy.domain.sms;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsService {
    SmsResult sendSms(Sms sms);
    List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt);
}
