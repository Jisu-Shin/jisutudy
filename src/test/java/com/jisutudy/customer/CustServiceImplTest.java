package com.jisutudy.customer;

import com.jisutudy.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CustServiceImplTest {

    AppConfig appConfig = new AppConfig();
    CustService custService = appConfig.custService();

    @Test
    @DisplayName("고객 등록하기")
    void join() {
        //given
        Cust cust = new Cust(1L,"01012345678", CustSmsConsentType.ALL_ALLOW);

        //when
        custService.join(cust);
        Cust findCust = custService.findMember(1L);

        //then
        assertThat(cust).isEqualTo(findCust);
    }

    @Test
    @DisplayName("전화번호로 고객 찾기")
    void findMember() {
        //given
        Cust cust = new Cust(1L,"01012345678", CustSmsConsentType.ALL_ALLOW);

        //when
        custService.join(cust);
        Cust findCust = custService.findMember("01012345678");

        //then
        assertThat(cust).isEqualTo(findCust);
    }

}