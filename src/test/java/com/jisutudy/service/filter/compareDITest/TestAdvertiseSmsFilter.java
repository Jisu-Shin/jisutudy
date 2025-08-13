package com.jisutudy.service.filter.compareDITest;

import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.repository.SmsSearch;
import com.jisutudy.service.filter.AdvertiseSmsFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestAdvertiseSmsFilter implements AdvertiseSmsFilter {

    @Override
    public boolean isSendable(Sms sms) {
        LocalDateTime startDt = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime endDt = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));

        SmsSearch smsSearch = SmsSearch.builder()
                .startDt(startDt)
                .endDt(endDt)
                .custId(sms.getCustId())
                .smsType(SmsType.ADVERTISING)
                .build();

        return true;
    }
}
