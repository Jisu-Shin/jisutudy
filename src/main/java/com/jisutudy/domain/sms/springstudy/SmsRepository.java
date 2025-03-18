package com.jisutudy.domain.sms.springstudy;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsRepository{
    void save(Sms sms);
    Sms findByCustId(Long custId);
    List<Sms> findListBySendDt(LocalDateTime startDt, LocalDateTime endDt);
}
