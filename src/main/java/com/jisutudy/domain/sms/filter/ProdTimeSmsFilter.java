package com.jisutudy.domain.sms.filter;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Profile("prod")
@Component
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
