package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.service.filter.AdvertiseSmsFilter;
import com.jisutudy.service.filter.SmsFilterImpl;
import com.jisutudy.service.filter.TimeSmsFilter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class SmsFilterImplBeforeDITest {

    @Mock
    private TimeSmsFilter timeSmsFilter;

    @Mock
    private AdvertiseSmsFilter advertiseSmsFilter;

    @InjectMocks
    private SmsFilterImpl smsFilter;

    private Sms createTestSms() {
        SmsTemplate template = SmsTemplate.createSmsTemplate("문자발송이요", SmsType.INFORMAITONAL);

        //todo null 사용안하는 방법
        long custId = 1L;

        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0));
        String sendDt = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return Sms.createSms(custId, template, null, sendDt, "01012345678");
    }

}