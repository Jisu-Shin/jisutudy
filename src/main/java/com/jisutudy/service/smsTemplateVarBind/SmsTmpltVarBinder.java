package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;

public interface SmsTmpltVarBinder {
    public String bind(SmsTemplate smsTemplate, BindingDto bindingDto);
}
