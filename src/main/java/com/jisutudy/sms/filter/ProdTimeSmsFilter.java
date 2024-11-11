package com.jisutudy.sms.filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Primary
public class ProdTimeSmsFilter implements TimeSmsFilter{
    @Override
    public boolean isSendable(LocalDateTime sendDt) {
        LocalTime smsTime = sendDt.toLocalTime();
        if (smsTime.isBefore(LocalTime.of(8,0))
            || smsTime.isAfter(LocalTime.of(20,0))) {
            return false;
        }
        return true;
    }
}
