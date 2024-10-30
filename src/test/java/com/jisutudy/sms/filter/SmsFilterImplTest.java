package com.jisutudy.sms.filter;

import com.jisutudy.customer.Cust;
import com.jisutudy.customer.CustRepository;
import com.jisutudy.customer.CustSmsConsentType;
import com.jisutudy.customer.MemoryCustRepository;
import com.jisutudy.sms.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SmsFilterImplTest {

    private final CustRepository custRepository = new MemoryCustRepository();
    private final SmsRepository smsRepository = new MemorySmsRepository();
    private final SmsFilter smsFilter = new SmsFilterImpl();

    @BeforeEach
    void init() {
        Cust cust = new Cust(2L, "01012345678", CustSmsConsentType.ALL_ALLOW);
        custRepository.save(cust);

        Cust cust2 = new Cust(3L, "01012345678", CustSmsConsentType.ALL_DENY);
        custRepository.save(cust2);

    }

    @Test
    @DisplayName("정상")
    void filter() {
        Sms sms = new Sms(1L, 2L, null, "문자발송", LocalDateTime.now(), SmsType.INFORMAITONAL);
        SmsResult smsResult = smsFilter.filter(sms);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.SUCCESS);
    }

    @Test
    @DisplayName("발송시간 필터링")
    void filterByTime() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(21,0));
        Sms sms = new Sms(1L, 2L, null, "문자발송", ldt, SmsType.INFORMAITONAL);
        SmsResult smsResult = smsFilter.filter(sms);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.NOT_SEND_TIME);
    }

    @Test
    @DisplayName("고객동의 필터링")
    void filterByCustConsent() {
        Sms sms = new Sms(1L, 3L, null, "문자발송", LocalDateTime.now(), SmsType.INFORMAITONAL);
        SmsResult smsResult = smsFilter.filter(sms);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.CUST_REJECT);
    }

    @Test
    @DisplayName("광고메시지 필터링")
    void filterByAdvertise() {
        Sms sms1 = new Sms(1L, 2L, null, "문자발송", LocalDateTime.now(), SmsType.ADVERTISING);
        Sms sms2 = new Sms(2L, 2L, null, "문자발송", LocalDateTime.now(), SmsType.ADVERTISING);
        Sms sms3 = new Sms(3L, 2L, null, "문자발송", LocalDateTime.now(), SmsType.ADVERTISING);
        smsRepository.save(sms1);
        smsRepository.save(sms2);
        SmsResult smsResult = smsFilter.filter(sms3);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.AD_COUNT_OVER);
    }
}