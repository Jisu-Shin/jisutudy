package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;

import java.util.List;

public class AdvertiseFilter {
    // 한 고객에게 광고성 문자는 하루에 2개만 발송할 수 있다.
    public boolean isSendable(List<Sms> todaySmsList, Sms sms){
        if (SmsType.ADVERTISING != sms.getSmsType()) {
            return true;
        }

        int cnt = 0;
        for (Sms todaySms : todaySmsList) {
            if (sms.getCustId().equals(todaySms.getCustId()) &&
                    SmsType.ADVERTISING == todaySms.getSmsType()) {
                cnt++;
            }
        }

        if (cnt >= 2) return false;
        return true;
    }
}
