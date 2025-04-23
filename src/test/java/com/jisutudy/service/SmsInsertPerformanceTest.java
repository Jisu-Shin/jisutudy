package com.jisutudy.service;


import com.jisutudy.repository.JpaSmsRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SmsInsertPerformanceTest {

    private static final int TEST_COUNT = 1000;
    @Autowired
    private JpaSmsRepository jpaSmsRepository;
    @Autowired
    private SmsFactory smsFactory;
    @Autowired
    private EntityManager em;

//    private List<Sms> generatedDummySms(int testCount) {
//        Cust cust = Cust.createCust("홍길동", "01012345678", CustSmsConsentType.ALL_ALLOW);
//        SmsTemplate smsTemplate = SmsTemplate.createSmsTemplate("샘플템플릿입니다", SmsType.INFORMAITONAL);
//        List<Sms> smsList = new ArrayList<>();
//        for (int i = 0; i < testCount; i++) {
//            smsList.add(Sms.createSms(cust, smsTemplate, "샘플템플릿입니다".concat(String.valueOf(0)), "202504151800"));
//        }
//        return smsList;
//    }

//    @Test
//    public void saveAll_jpa_insert_perfomance() throws Exception {
//        List<Sms> smsList = generatedDummySms(TEST_COUNT);
//
//        long start = System.currentTimeMillis();
//        jpaSmsRepository.saveAll(smsList);
//        long end = System.currentTimeMillis();
//
//        System.out.println("✅ JPA saveAll() 소요 시간: " + (end - start) + "ms");
//    }
//
//    @Test
//    public void queryDsl_bulk_insert_performance() throws Exception {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//    }

}
