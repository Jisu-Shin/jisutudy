package com.jisutudy.sms.filter;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.JpaCustRepository;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.domain.sms.filter.SmsFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active=prod")
class SmsFilterImplTest {

//    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    SmsFilter smsFilter = ac.getBean(SmsFilter.class);
//    TimeSmsFilter timeSmsFilter = ac.getBean(ProdTimeSmsFilter.class);

    //    SmsRepository smsRepository = ac.getBean(SmsRepository.class);
//    JpaSmsRepository smsRepository = ac.getBean(JpaSmsRepository.class);

//    CustRepository custRepository = ac.getBean(CustRepository.class);
//    JpaCustRepository custRepository = ac.getBean(JpaCustRepository.class);

    @Autowired
    SmsFilter smsFilter;

    @Autowired
    JpaSmsRepository smsRepository;

    @Autowired
    JpaCustRepository custRepository;

    @BeforeEach
    void init() {
        Cust cust = Cust.builder()
                .name("홍길동")
                .phoneNumber("01012345678")
                .smsConsentType(CustSmsConsentType.ALL_ALLOW)
                .build();
        custRepository.save(cust);

        Cust cust2 = Cust.builder()
                .name("고길동")
                .phoneNumber("01098765432")
                .smsConsentType(CustSmsConsentType.ALL_DENY)
                .build();
        custRepository.save(cust2);

    }

    @AfterEach
    void destroy() {
        custRepository.deleteAll();
    }

    @Test
    @DisplayName("정상")
    void filter() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        Sms sms = new Sms(1L, 2L, null, "문자발송", ldt, SmsType.INFORMAITONAL);
//        SmsResult smsResult = smsFilter.filter(sms);

        Optional<Cust> cust = custRepository.findByPhoneNumber("01012345678");

        SmsResult smsResult = smsFilter.filter(sms, cust.get());
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.SUCCESS);
    }

    @Test
    @DisplayName("발송시간 필터링")
    void filterByTime() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(21,0));
        Sms sms = new Sms(1L, 2L, null, "문자발송", ldt, SmsType.INFORMAITONAL);

        Optional<Cust> cust = custRepository.findByPhoneNumber("01012345678");

        SmsResult smsResult = smsFilter.filter(sms, cust.get());
        Assertions.assertThat(smsResult).isEqualTo(SmsResult.NOT_SEND_TIME);
    }

//    @Test
//    @DisplayName("timesmsfilter 필터링")
//    void filterByTimeSmsFilter() {
//        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(21,0));
//        boolean sendable = timeSmsFilter.isSendable(ldt);
//        Assertions.assertThat(sendable).isEqualTo(false);
//    }


    @Test
    @DisplayName("고객동의 필터링")
    void filterByCustConsent() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        Sms sms = new Sms(1L, 3L, null, "문자발송", ldt, SmsType.INFORMAITONAL);

        Optional<Cust> cust = custRepository.findByPhoneNumber("01098765432");

        SmsResult smsResult = smsFilter.filter(sms, cust.get());

        Assertions.assertThat(smsResult).isEqualTo(SmsResult.CUST_REJECT);
    }

    @Test
    @DisplayName("광고메시지 필터링")
    void filterByAdvertise() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0));
        Sms sms1 = new Sms(1L, 2L, null, "문자발송", ldt, SmsType.ADVERTISING);
        Sms sms2 = new Sms(2L, 2L, null, "문자발송", ldt, SmsType.ADVERTISING);
        smsRepository.save(sms1);
        smsRepository.save(sms2);

        Sms sms3 = new Sms(3L, 2L, null, "문자발송", ldt, SmsType.ADVERTISING);
        Optional<Cust> cust = custRepository.findByPhoneNumber("01012345678");
        SmsResult smsResult = smsFilter.filter(sms3, cust.get());

        Assertions.assertThat(smsResult).isEqualTo(SmsResult.AD_COUNT_OVER);
    }
}