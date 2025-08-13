package com.jisutudy.service.filter.timeSmsFilter;

import java.time.LocalDateTime;

public interface TimeSmsFilter {
    boolean isSendable(LocalDateTime sendDt);
}
