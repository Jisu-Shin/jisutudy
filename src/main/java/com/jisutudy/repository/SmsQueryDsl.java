package com.jisutudy.repository;

import com.jisutudy.domain.sms.Sms;

import java.util.List;

public interface SmsQueryDsl {
    public List<Sms> findBySendDt(SmsSearch smsSearch);
}
