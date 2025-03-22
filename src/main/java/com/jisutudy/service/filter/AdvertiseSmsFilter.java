package com.jisutudy.service.filter;

import com.jisutudy.domain.sms.Sms;

public interface AdvertiseSmsFilter {
    boolean isSendable(Sms sms);
}
