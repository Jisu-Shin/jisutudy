package com.jisutudy.sms.filter;

import com.jisutudy.sms.Sms;

import java.util.List;

public class AdvertiseFilter {
    // 한 고객에게 광고성 문자는 하루에 2개만 발송할 수 있다.
    public boolean isSendable(List<Sms> todaySmsList, Long custId){
        int cnt = 0;
        for (Sms sms : todaySmsList) {
            if (sms.getCustId().equals(custId)) {
                cnt++;
            }
        }

        if (cnt >= 2) return false;
        return true;
    }
}
