package com.jisutudy.service.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustConsentFilterTest {

    CustConsentFilter custConsentFilter;

    @BeforeEach
    void setUp() {
        custConsentFilter = new CustConsentFilter();
    }

    @Test
    public void 모두허용() throws Exception {
        //given

        //when
        boolean sendable = custConsentFilter.isSendable(CustSmsConsentType.ALL_ALLOW, SmsType.INFORMAITONAL);
        boolean sendable2 = custConsentFilter.isSendable(CustSmsConsentType.ALL_ALLOW, SmsType.VERIFICATION);
        boolean sendable3 = custConsentFilter.isSendable(CustSmsConsentType.ALL_ALLOW, SmsType.ADVERTISING);

        //then
        Assertions.assertEquals(true, sendable);
        Assertions.assertEquals(true, sendable2);
        Assertions.assertEquals(true, sendable3);
    }

    @Test
    public void 고객유형_모두거부() throws Exception {
        //given

        //when
        boolean infoResult = custConsentFilter.isSendable(CustSmsConsentType.ALL_DENY, SmsType.INFORMAITONAL);
        boolean verifResult = custConsentFilter.isSendable(CustSmsConsentType.ALL_DENY, SmsType.VERIFICATION);
        boolean adverResult = custConsentFilter.isSendable(CustSmsConsentType.ALL_DENY, SmsType.ADVERTISING);

        //then
        Assertions.assertEquals(false, infoResult);
        Assertions.assertEquals(true, verifResult); // 인증은 항상 나가야 함
        Assertions.assertEquals(false, adverResult);
    }

    @Test
    public void 고객유형_광고거부() throws Exception {
        //given

        //when
        boolean infoResult = custConsentFilter.isSendable(CustSmsConsentType.ADVERTISE_DENY, SmsType.INFORMAITONAL);
        boolean verifResult = custConsentFilter.isSendable(CustSmsConsentType.ADVERTISE_DENY, SmsType.VERIFICATION);
        boolean adverResult = custConsentFilter.isSendable(CustSmsConsentType.ADVERTISE_DENY, SmsType.ADVERTISING);

        //then
        Assertions.assertEquals(true, infoResult);
        Assertions.assertEquals(true, verifResult);
        Assertions.assertEquals(false, adverResult);
    }

}