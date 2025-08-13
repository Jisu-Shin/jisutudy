package com.jisutudy.service.filter.advertiseSmsFilter;

import com.jisutudy.domain.sms.Sms;

public interface AdvertiseSmsFilter {
    boolean isSendable(Sms sms);
}
