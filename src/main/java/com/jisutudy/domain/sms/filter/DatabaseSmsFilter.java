package com.jisutudy.domain.sms.filter;

import com.jisutudy.repository.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.repository.SmsSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseSmsFilter implements AdvertiseSmsFilter {

    // 리포지토리 버전2) JPA 리포지토리
    private final JpaSmsRepository jpaSmsRepository;

    @Override
    public boolean isSendable(Sms sms) {
        LocalDateTime startDt = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime endDt = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));

        SmsSearch smsSearch = SmsSearch.builder()
                .startDt(startDt)
                .endDt(endDt)
                .custId(sms.getCust().getId())
                .smsType(SmsType.ADVERTISING)
                .build();
        List<Sms> smsList = jpaSmsRepository.findBySendDt(smsSearch);
//        List<Sms> todaySmsList = jpaSmsRepository.findAllBySendDtBetween(startDt, endDt);

        // 한 고객에게 광고성 문자는 하루에 2개만 발송할 수 있다.
        if (smsList.size() >= 2) return false;
        return true;
    }
}
