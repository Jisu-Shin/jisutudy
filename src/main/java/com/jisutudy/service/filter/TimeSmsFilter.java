package com.jisutudy.service.filter;

import java.time.LocalDateTime;

public interface TimeSmsFilter {
    boolean isSendable(LocalDateTime sendDt);
}
