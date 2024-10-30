package com.jisutudy.sms;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsService {
    SmsResult sendSms(Sms sms);
    List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt);
}
