package com.jisutudy.domain.sms.filter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Profile("dev")
@Component
public class TestTimeSmsFilter implements TimeSmsFilter{
    @Override
    public boolean isSendable(LocalDateTime sendDt) {
        // 시간제한이 없다
        return true;
    }
}
