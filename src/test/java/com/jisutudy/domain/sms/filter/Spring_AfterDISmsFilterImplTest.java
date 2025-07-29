package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Spring_AfterDISmsFilterImplTest.TestConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Spring_AfterDISmsFilterImplTest {

    // 성능 측정 결과를 저장할 정적 변수들
    private static List<Long> afterDIExecutionTimes = new ArrayList<>();
    private static long totalAfterDItime = 0;

    @Autowired
    private SmsFilterImpl smsFilter;

    @AfterAll
    static void cleanUp() {
        System.out.println("\n===DI 적용 후 테스트 완료===");
    }

    private Sms createTestSms() {
        SmsTemplate template = SmsTemplate.createSmsTemplate("문자발송테스트", SmsType.INFORMAITONAL);

        //todo null 사용안하는 방법
        long custId = 1L;

        String sendDt = LocalDateTime.of(2025, 7, 29, 19, 00).format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return Sms.createSms(custId, template, null, sendDt, "01012345678");
    }

    @Test
    @Order(1)
    @DisplayName("DI 적용 후 - 객체 생성 성능 테스트")
    void testPerformanceAfterDI() {
        //given
        Sms sms = createTestSms();

        //when
        long startTime = System.nanoTime();
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("객체 생성 성능 테스트");
        System.out.println("DI 적용 후 실행시간: " + duration / 1_000_000.0 + "ms");

        //then
        Assertions.assertEquals(SmsResult.SUCCESS, result);
    }

    @RepeatedTest(100)
    @Order(2)
    @DisplayName("DI 적용 후 - 100회 반복 성능 측정")
    public void testRepeatedExecutionPerformance(RepetitionInfo repetitionInfo) throws Exception {
        //given
        Sms sms = createTestSms();

        //when
        long startTime = System.nanoTime();
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);
        long endTime = System.nanoTime();

        //then
        long duration = endTime - startTime;
        afterDIExecutionTimes.add(duration);
        totalAfterDItime += duration;

        //then
        Assertions.assertEquals(SmsResult.SUCCESS, result);

        // 마지막 반복에서 통계 출력
        if (repetitionInfo.getCurrentRepetition() == repetitionInfo.getTotalRepetitions()) {
            double averageTime = totalAfterDItime / (double) afterDIExecutionTimes.size();
            long minTime = afterDIExecutionTimes.stream().min(Long::compareTo).orElse(0L);
            long maxTime = afterDIExecutionTimes.stream().max(Long::compareTo).orElse(0L);

            System.out.println("\n=== DI 적용 후 성능 통계 (100회 실행) ===");
            System.out.println("평균 실행시간: " + averageTime / 1_000_000.0 + "ms");
            System.out.println("최소 실행시간: " + minTime / 1_000_000.0 + "ms");
            System.out.println("최대 실행시간: " + maxTime / 1_000_000.0 + "ms");
            System.out.println("총 실행시간: " + totalAfterDItime / 1_000_000.0 + "ms");
        }
    }

    @Test
    @Order(3)
    @DisplayName("DI 적용 후 - 메모리 사용량 추정")
    public void testMemoryUsage() throws Exception {
        //given
        Sms sms = createTestSms();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // 가비지 컬렉션 실행

        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        //when - 여러 번 실행하여 객체 생성 비용 측정
        for (int i = 0; i < 1000; i++) {
            smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);
            // 1번 new CustConsentFilter() 실행됨
        }

        runtime.gc(); // 가비지 컬렉션 실행
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();

        //then
        long memoryUsed = afterMemory - beforeMemory;
        System.out.println("\nDI 적용 후 메모리 사용량 (1000회): " + memoryUsed / 1024.0 + "KB");
    }

    static class TestConfig {
        @Bean
        public TimeSmsFilter timeSmsFilter() {
            return new TestTimeSmsFilter();
        }

        @Bean
        public CustomerSmsFilter customerSmsFilter() {
            return new CustConsentFilter();
        }

        @Bean
        public AdvertiseSmsFilter advertiseSmsFilter() {
            return new TestAdvertiseSmsFilter();
        }

        @Bean
        public SmsFilterImpl smsFilter(TimeSmsFilter timeSmsFilter, AdvertiseSmsFilter advertiseSmsFilter, CustomerSmsFilter customerSmsFilter) {
            return new SmsFilterImpl(timeSmsFilter, advertiseSmsFilter, customerSmsFilter); // 생성자주입
        }

    }
}
