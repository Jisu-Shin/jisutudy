package com.jisutudy.service.filter.compareDITest;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.advertiseSmsFilter.AdvertiseSmsFilter;
import com.jisutudy.service.filter.customerSmsFilter.CustomerSmsFilter;
import com.jisutudy.service.filter.SmsFilterImpl;
import com.jisutudy.service.filter.timeSmsFilter.TimeSmsFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class Mock_AfterDISmsFilterImplTest {

    // @Mock : 자동으로 Mock 객체 생성
    @Mock
    private TimeSmsFilter timeSmsFilter;
    @Mock
    private AdvertiseSmsFilter advertiseSmsFilter;
    @Mock
    private CustomerSmsFilter customerSmsFilter;

    // @InjectMocks : Mock들이 자동으로 주입됨
    @InjectMocks
    private SmsFilterImpl smsFilter;

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
        when(customerSmsFilter.isSendable(any(), any())).thenReturn(true);
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
        when(customerSmsFilter.isSendable(any(), any())).thenReturn(true);
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
        when(customerSmsFilter.isSendable(any(), any())).thenReturn(true);
        when(advertiseSmsFilter.isSendable(any())).thenReturn(true);

        // when 절 없음 → 실제 로직이 동작함
        SmsResult result = smsFilter.filter(sms, CustSmsConsentType.ALL_ALLOW);

        assertEquals(SmsResult.SUCCESS, result);

        // verify : 특정 mock 객체의 메서드가 호출되었는지 여부를 검증
        verify(timeSmsFilter).isSendable(any());
        verify(customerSmsFilter).isSendable(any(), any());
        verify(advertiseSmsFilter).isSendable(any());
    }

}