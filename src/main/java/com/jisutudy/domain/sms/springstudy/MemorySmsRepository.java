package com.jisutudy.domain.sms.springstudy;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemorySmsRepository implements SmsRepository {
    private static Map<Long, Sms> store = new ConcurrentHashMap<>();

    @Override
    public void save(Sms sms) {
        store.put(sms.getSmsId(), sms);
    }

    @Override
    public Sms findByCustId(Long custId) {
        Sms findSms = null;
        for (Sms s : store.values()) {
            if (s.getCustId().equals(custId)){
                findSms = s;
            }
        }
        return findSms;
    }

    @Override
    public List<Sms> findListBySendDt(LocalDateTime startDt, LocalDateTime endDt) {
        List<Sms> findSmsList = new ArrayList<>();
        for (Sms s : store.values()) {
            if (s.getSendDt().isAfter(startDt) && s.getSendDt().isBefore(endDt)) {
                findSmsList.add(s);
            }
        }
        return findSmsList;
    }
}
