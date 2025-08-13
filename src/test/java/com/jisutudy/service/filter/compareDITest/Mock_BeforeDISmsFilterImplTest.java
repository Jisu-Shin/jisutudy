package com.jisutudy.service.filter.compareDITest;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.advertiseSmsFilter.AdvertiseSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TimeSmsFilter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

    // mock 테스트라 발송시간 세팅 상관 없음
    // 정보성문자
    private Sms createInformationSms() {
        SmsTemplate template = SmsTemplate.createSmsTemplate("정보성문자", SmsType.INFORMAITONAL);
        Sms sms = Sms.createSms(1L, template, null,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")), "01012345678");

        return sms;
    }

    // 인증성문자
    private Sms createVerificationSms() {
        SmsTemplate template = SmsTemplate.createSmsTemplate("인증문자입니다 ****", SmsType.VERIFICATION);
        Sms sms = Sms.createSms(1L, template, null,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")), "01012345678");

        return sms;
    }

    // 광고성문자
    private Sms createAdvertisingSms() {
        SmsTemplate template = SmsTemplate.createSmsTemplate("광고문자입니다", SmsType.ADVERTISING);
        Sms sms = Sms.createSms(1L, template, null,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")), "01012345678");

        return sms;
    }

    @Test
    public void 발송시간제한_인증문자_SUCCESS() throws Exception {
        //given
        Sms sms = createVerificationSms();

        //when
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);

        //then
        assertEquals(SmsResult.SUCCESS, result);
    }

    @Test
    public void 발송시간제한_NOTSEND() throws Exception {
        //given
        Sms sms = createInformationSms();
        Sms sms2 = createAdvertisingSms();
        when(timeSmsFilter.isSendable(any())).thenReturn(false);

        //when
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);
        SmsResult result2 = smsFilter.filter(sms2, CustSmsConsentType.ALL_ALLOW);
        SmsResult result3 = smsFilter.filter(sms2, CustSmsConsentType.ALL_DENY);
        SmsResult result4 = smsFilter.filter(sms2, CustSmsConsentType.ADVERTISE_DENY);

        //then
        assertEquals(SmsResult.NOT_SEND_TIME, result);
        assertEquals(SmsResult.NOT_SEND_TIME, result2);
        assertEquals(SmsResult.NOT_SEND_TIME, result3);
        assertEquals(SmsResult.NOT_SEND_TIME, result4);
    }

    @Test
    public void 발송시간_광고개수허용_SUCCESS() throws Exception {
        //given
        Sms sms = createAdvertisingSms();
        when(timeSmsFilter.isSendable(any())).thenReturn(true);
        when(advertiseSmsFilter.isSendable(any())).thenReturn(true);

        //when
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);

        //then
        assertEquals(SmsResult.SUCCESS, result);
    }

    @Test
    public void 발송시간_광고개수제한_AD_COUNT_OVER() throws Exception {
        //given
        Sms sms = createAdvertisingSms();
        when(timeSmsFilter.isSendable(any())).thenReturn(true);
        when(advertiseSmsFilter.isSendable(any())).thenReturn(false); // 광고개수제한

        //when
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);

        //then
        assertEquals(SmsResult.AD_COUNT_OVER, result);
    }

    @Test
    public void 발송시간_고객동의_CUST_REJECT() throws Exception {
        //given
        Sms sms = createInformationSms();
        when(timeSmsFilter.isSendable(any())).thenReturn(true);

        //when
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_DENY);

        //then
        assertEquals(SmsResult.CUST_REJECT, result);
    }

    @Test
    void 동의하지_않은_고객이면_CUST_REJECT() {
        SmsTemplate adTemplate = SmsTemplate.createSmsTemplate("광고문자", SmsType.ADVERTISING);
        Sms sms = Sms.createSms(1L, adTemplate, null,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")), "01012345678");
        when(timeSmsFilter.isSendable(any())).thenReturn(true);

        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_DENY);

        assertEquals(SmsResult.CUST_REJECT, result);
    }
    
    @Test
    public void DI미적용_내부_호출검증불가() throws Exception {
        Sms sms = createAdvertisingSms();
        when(timeSmsFilter.isSendable(any())).thenReturn(true);
        when(advertiseSmsFilter.isSendable(any())).thenReturn(true);

        // when 절 없음 → 실제 로직이 동작함
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);

        assertEquals(SmsResult.SUCCESS, result);

        // verify가 안 먹힘
        // verify : 특정 mock 객체의 메서드가 호출되었는지 여부를 검증
        verify(timeSmsFilter).isSendable(any());
//        verify(customerSmsFilter).isSendable(any()); // ❌ 여기서 예외 발생 또는 실패
        verify(advertiseSmsFilter).isSendable(any());
    }

}