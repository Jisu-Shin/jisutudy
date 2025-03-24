package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.customer.Cust;

public interface SmsTmpltVarBinder {
    public String bind(SmsTemplate smsTemplate, Cust cust);
}
