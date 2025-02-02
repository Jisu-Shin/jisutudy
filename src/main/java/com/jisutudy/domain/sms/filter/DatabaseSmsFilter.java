package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.sms.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;
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


        // TODO Jpa 조회할때 고객이랑 일자만 받아서 해당 고객으로 보낸 문자만 추출하기
        List<Sms> todaySmsList = jpaSmsRepository.findAllBySendDtBetween(startDt, endDt);

        // 한 고객에게 광고성 문자는 하루에 2개만 발송할 수 있다.
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
