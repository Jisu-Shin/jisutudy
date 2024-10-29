package com.jisutudy.sms;

import com.jisutudy.customer.Cust;
import com.jisutudy.customer.CustService;
import com.jisutudy.customer.CustServiceImpl;
import com.jisutudy.customer.CustSmsConsentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SmsServiceImplTest {

    @BeforeAll
    static void sendSmsList() {
        Cust c6 = new Cust(1L, "666", CustSmsConsentType.ALL_ALLOW);
        Cust c7 = new Cust(2L, "777", CustSmsConsentType.ALL_ALLOW);
        Cust c8 = new Cust(3L, "888", CustSmsConsentType.ALL_DENY);
        CustService custService = new CustServiceImpl();
        custService.join(c6);
        custService.join(c7);
        custService.join(c8);
    }

    @Test
    void sendSms() {
        Sms s1 = new Sms(1L, 6L, null ,"문자내용1", LocalDateTime.now(),SmsType.INFORMAITONAL);
        Sms s2 = new Sms(2L, 7L, null ,"문자내용2", LocalDateTime.now(),SmsType.ADVERTISING);
        Sms s3 = new Sms(3L, 8L, null ,"문자내용3", LocalDateTime.now(),SmsType.VERIFICATION);
        Sms s4 = new Sms(4L, 6L, null ,"문자내용4", LocalDateTime.now(),SmsType.ADVERTISING);
        Sms s5 = new Sms(5L, 6L, null ,"문자내용5", LocalDateTime.now(),SmsType.ADVERTISING);
    }

    @Test
    void findSmsList() {
    }
}