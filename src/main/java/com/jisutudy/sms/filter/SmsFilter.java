package com.jisutudy.sms.filter;

import com.jisutudy.customer.Cust;
import com.jisutudy.sms.Sms;
import com.jisutudy.sms.SmsResult;

public interface SmsFilter {
    SmsResult filter(Sms sms);
}
