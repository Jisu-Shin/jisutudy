package com.jisutudy.service.filter;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;

public interface SmsFilter {
    SmsResult filter(Sms sms);
}
