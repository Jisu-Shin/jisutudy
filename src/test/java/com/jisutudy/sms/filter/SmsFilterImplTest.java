package com.jisutudy.sms.filter;

import com.jisutudy.AppConfig;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.springstudy.CustRepository;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.springstudy.SmsRepository;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.domain.sms.filter.SmsFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class SmsFilterImplTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    SmsFilter smsFilter = ac.getBean(SmsFilter.class);
    SmsRepository smsRepository = ac.getBean(SmsRepository.class);
    CustRepository custRepository = ac.getBean(CustRepository.class);

    @BeforeEach
    void init() {
        Cust cust = new Cust(2L,"홍길동", "01012345678", CustSmsConsentType.ALL_ALLOW);
        custRepository.save(cust);

        Cust cust2 = new Cust(3L,"홍길동", "01012345678", CustSmsConsentType.ALL_DENY);
        custRepository.save(cust2);

    }

    @Test
    @DisplayName("정상")
    void filter() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        Sms sms = new Sms(1L, 2L, null, "문자발송", ldt, SmsType.INFORMAITONAL);
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
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        Sms sms = new Sms(1L, 3L, null, "문자발송", ldt, SmsType.INFORMAITONAL);
        SmsResult smsResult = smsFilter.filter(sms);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.CUST_REJECT);
    }

    @Test
    @DisplayName("광고메시지 필터링")
    void filterByAdvertise() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        Sms sms1 = new Sms(1L, 2L, null, "문자발송", ldt, SmsType.ADVERTISING);
        Sms sms2 = new Sms(2L, 2L, null, "문자발송", ldt, SmsType.ADVERTISING);
        Sms sms3 = new Sms(3L, 2L, null, "문자발송", ldt, SmsType.ADVERTISING);
        smsRepository.save(sms1);
        smsRepository.save(sms2);
        SmsResult smsResult = smsFilter.filter(sms3);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.AD_COUNT_OVER);
    }
}