package com.jisutudy.sms;

import com.jisutudy.AppConfig;
import com.jisutudy.customer.Cust;
import com.jisutudy.customer.CustService;
import com.jisutudy.customer.CustSmsConsentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmsServiceImplTest {

//    static AppConfig appConfig = new AppConfig();
//    static CustService custService = appConfig.custService();
//    static SmsService smsService = appConfig.smsService();
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    CustService custService = ac.getBean("custService", CustService.class);
    SmsService smsService = ac.getBean("smsService", SmsService.class);

    @BeforeEach
    void sendSmsList() {
        Cust c6 = new Cust(6L, "666", CustSmsConsentType.ALL_ALLOW);
        Cust c7 = new Cust(7L, "777", CustSmsConsentType.ALL_ALLOW);
        Cust c8 = new Cust(8L, "888", CustSmsConsentType.ALL_DENY);
        custService.join(c6);
        custService.join(c7);
        custService.join(c8);
    }

    @Test
    void sendSms() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(19,0));
        Sms[] smsList = new Sms[5];
        smsList[0] = new Sms(1L, 6L, null ,"문자내용1", ldt,SmsType.INFORMAITONAL);
        smsList[1] = new Sms(2L, 7L, null ,"문자내용2", ldt,SmsType.ADVERTISING);
        smsList[2] = new Sms(3L, 8L, null ,"문자내용3", ldt,SmsType.VERIFICATION);
        smsList[3] = new Sms(4L, 7L, null ,"문자내용4", ldt,SmsType.ADVERTISING);
        smsList[4] = new Sms(5L, 7L, null ,"문자내용5", ldt,SmsType.ADVERTISING);
        for (Sms sms : smsList) {
            System.out.println(smsService.sendSms(sms));
        }
    }

    @Test
    void findSmsList() {
        sendSms();
        List<Sms> smsList = smsService.findSmsList(LocalDate.now().atTime(0, 0), LocalDate.now().plusDays(1).atTime(0, 0));
        Assertions.assertThat(smsList.size()).isEqualTo(5);
    }
}