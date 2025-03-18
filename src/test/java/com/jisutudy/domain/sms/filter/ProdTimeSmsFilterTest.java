package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.sms.filter.ProdTimeSmsFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ProdTimeSmsFilterTest {

    @Test
    @DisplayName("시간필터링 테스트 : 8시 이전")
    void isSendableBefor8(){
        ProdTimeSmsFilter filter = new ProdTimeSmsFilter();
        LocalDateTime testDt = LocalDateTime.of(2024,10,29,7,30);
        boolean sendable = filter.isSendable(testDt);
        Assertions.assertThat(sendable).isFalse();
    }

    @Test
    @DisplayName("시간필터링 테스트 : 성공")
    void isSendable(){
        ProdTimeSmsFilter filter = new ProdTimeSmsFilter();
        LocalDateTime testDt = LocalDateTime.of(2024,10,29,17,30);
        boolean sendable = filter.isSendable(testDt);
        Assertions.assertThat(sendable).isTrue();
    }

    @Test
    @DisplayName("시간필터링 테스트 : 20시 이후")
    void isSendableAfter20(){
        ProdTimeSmsFilter filter = new ProdTimeSmsFilter();
        LocalDateTime testDt = LocalDateTime.of(2024,10,29,20,30);
        boolean sendable = filter.isSendable(testDt);
        Assertions.assertThat(sendable).isFalse();
    }

}