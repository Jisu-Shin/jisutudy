package com.jisutudy.domain.sms.springstudy;

import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsService {
    SmsResult sendSms(Sms sms);
    List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt);
}
