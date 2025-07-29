package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.SmsFilter;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(properties = "spring.profiles.active=prod")
@Transactional
class SmsFilterImplTest {

    @Autowired
    SmsFilter smsFilter;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("정상")
    void filter() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0));
        String sendDt = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        Long custId = 1L;
        SmsTemplate smsTemplate = createTemplate("안녕하세요 문자발송요", SmsType.INFORMAITONAL);
        Sms sms = Sms.createSms(custId, smsTemplate, null, sendDt, "01098765412");

        SmsResult smsResult = smsFilter.filter(sms);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.SUCCESS);
    }

    @Test
    @DisplayName("발송시간 필터링 - 발송불가")
    void filterByTime() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 0));
        String sendDt = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        Long custId = 1L;
        SmsTemplate smsTemplate = createTemplate("안녕하세요 문자발송요", SmsType.INFORMAITONAL);
        Sms sms = Sms.createSms(custId, smsTemplate, null, sendDt, "01098765412");

        SmsResult smsResult = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.NOT_SEND_TIME);
    }

    @Test
    @DisplayName("고객마케팅동의 필터링")
    void filterByCustConsent() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        String sendDt = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        Long custId = 1L;
        SmsTemplate smsTemplate = createTemplate("안녕하세요 문자발송요", SmsType.INFORMAITONAL);
        Sms sms = Sms.createSms(custId, smsTemplate, null, sendDt, "01098765412");

        SmsResult smsResult = smsFilter.filter(sms, CustSmsConsentType.ALL_DENY);

        Assertions.assertThat(smsResult).isEqualTo(SmsResult.CUST_REJECT);
    }

    @Test
    @DisplayName("광고메시지 필터링")
    void filterByAdvertise() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        String sendDt = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        Long custId = 1L;
        SmsTemplate smsTemplate = createTemplate("광고문자입니다~", SmsType.ADVERTISING);
        Sms sms1 = Sms.createSms(custId, smsTemplate, smsTemplate.getTemplateContent()+"1", sendDt, "01098765412");
        Sms sms2 = Sms.createSms(custId, smsTemplate, smsTemplate.getTemplateContent()+"2", sendDt, "01098765412");
        em.persist(sms1);
        em.persist(sms2);

        Sms sms3 = Sms.createSms(custId, smsTemplate, smsTemplate.getTemplateContent()+"3", sendDt, "01098765412");
        SmsResult smsResult = smsFilter.filter(sms3, CustSmsConsentType.ALL_ALLOW);

        Assertions.assertThat(smsResult).isEqualTo(SmsResult.AD_COUNT_OVER);
    }

    private SmsTemplate createTemplate(String templateContent, SmsType smsType) {
        SmsTemplate smsTemplate = SmsTemplate.createSmsTemplate(templateContent, smsType);
        em.persist(smsTemplate);
        return smsTemplate;
    }
}