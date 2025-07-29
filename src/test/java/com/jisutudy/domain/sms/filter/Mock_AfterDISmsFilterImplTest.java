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

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Mock_AfterDISmsFilterImplTest {

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

        String sendDt = LocalDateTime.of(2025, 7, 29, 19, 00).format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
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
