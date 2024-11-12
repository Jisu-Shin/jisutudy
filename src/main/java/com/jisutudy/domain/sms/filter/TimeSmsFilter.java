package com.jisutudy.domain.sms.filter;

import java.time.LocalDateTime;

public interface TimeSmsFilter {
    boolean isSendable(LocalDateTime sendDt);
}
