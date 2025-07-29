package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.AdvertiseSmsFilter;
import com.jisutudy.service.filter.TimeSmsFilter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/*
======= @ExtendWith(MockitoExtension.class) 설명 =======
- JUnit 5에서 Mockito를 사용하기 위한 확장(Extenstion) 등록
- 이 어노테이션이 있어야 @Mock, @InjectMocks 등이 작동함
- JUnit 5의 Extenstion 모델을 통해 Mockito 기능을 통합


======= @TestMethodOrder(MethodOrderer.OrderAnnotation.class) =======
- JUnit 5에서 테스트 메소드 실행 순서를 제어
- 기본적으로 JUnit은 테    스트 순서를 보장하지 않음 (독립성 원칙)
- 하지만 성능 측정이나 통합 테스트에서는 순서가 중요할 수 있음
- @Order(n)로 순서 지정 및 실행
- 어노테이션 기반 순서, 메소드명 기반 순서(알파벳 순), DisplayName 기반 순서, 랜덤순서


======= 🚨주의사항 =======
1. @ExtendWith(MockitoExtension.class) 없이 @Mock 사용하면 NullPointerException 발생
2. 테스트 순서에 의존하는 설계는 일반적으로 피해야 함 (테스트 독립성원칙)
3. @Order는 성능 측정, 통합 테스트 등 특별한 경웽만 사용


======= ✅Best Practice =======
1. 단위 테스트는 순서 없이 독립적으로 작성
2. 성능 비교나 데이터 수집이 필요한 경우에만 @Order 사용
3. @ExtendsWith는 필요한 Extension만 명시적으로 추가
4. static 변수로 데이터 공유 시 @AfterAll에서 정리


 */
@ExtendWith(MockitoExtension.class)
class Mock_BeforeDISmsFilterImplTest {

    // @Mock : 자동으로 Mock 객체 생성
    @Mock
    private TimeSmsFilter timeSmsFilter;
    @Mock
    private AdvertiseSmsFilter advertiseSmsFilter;

    // @InjectMocks : Mock들이 자동으로 주입됨
    @InjectMocks
    private SmsFilterBeforeDIImpl smsFilter;

    private Sms createTestSms() {
        SmsTemplate template = SmsTemplate.createSmsTemplate("문자발송테스트", SmsType.INFORMAITONAL);

        //todo null 사용안하는 방법
        long custId = 1L;

        String sendDt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return Sms.createSms(custId, template, null, sendDt, "01012345678");
    }

    @Test
    @DisplayName("CustConsentFilter Mock 불가능으로 인한 테스트 제약")
    public void testMockingLimitationWithFullLogic() throws Exception {
        //given
        SmsTemplate adTemplate = SmsTemplate.createSmsTemplate("광고문자", SmsType.ADVERTISING);
        Sms adSms = Sms.createSms(1L, adTemplate, null,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")), "01012345678");

        when(timeSmsFilter.isSendable(any())).thenReturn(true);
        when(advertiseSmsFilter.isSendable(any())).thenReturn(false); // 광고용 모킹 추가!

        //when
        SmsResult result = smsFilter.filter(adSms, CustSmsConsentType.ALL_ALLOW);

        //then
        Assertions.assertEquals(SmsResult.AD_COUNT_OVER, result);

        System.out.println("\n제약사항 : CustConsentFilter의 실제 로직에 의존하여 테스트");
        System.out.println("- Mock으로 제어할 수 없음");
        System.out.println("- 모든 필터링 로직을 거치지만 CustConsentFilter만 제어 불가");
        System.out.println("- 일반/광고 SMS 모두 동일한 제약사항 존재");
        System.out.println("- 단위 테스트 순수성 훼손 및 시나리오 테스트 어려움");
    }


}