package com.jisutudy.sms.filter;

import java.time.LocalDateTime;

public interface TimeSmsFilter {
    boolean isSendable(LocalDateTime sendDt);
}
