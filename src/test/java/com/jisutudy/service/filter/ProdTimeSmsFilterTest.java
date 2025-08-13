package com.jisutudy.service.filter;

import com.jisutudy.service.filter.timeSmsFilter.ProdTimeSmsFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ProdTimeSmsFilterTest {

    ProdTimeSmsFilter timeSmsFilter;

    @BeforeEach
    void setUp() {
        timeSmsFilter = new ProdTimeSmsFilter();
    }

    @Test
    @DisplayName("시간필터링 테스트 : 8시 이전")
    void isSendableBefore8() {
        LocalDateTime testDt = LocalDateTime.of(2024, 10, 29, 7, 30);
        boolean sendable = timeSmsFilter.isSendable(testDt);
        Assertions.assertThat(sendable).isFalse();
    }

    @Test
    @DisplayName("시간필터링 테스트 : 성공")
    void isSendable() {
        LocalDateTime testDt = LocalDateTime.of(2024, 10, 29, 17, 30);
        boolean sendable = timeSmsFilter.isSendable(testDt);
        Assertions.assertThat(sendable).isTrue();
    }

    @Test
    @DisplayName("시간필터링 테스트 : 20시 이후")
    void isSendableAfter20() {
        LocalDateTime testDt = LocalDateTime.of(2024, 10, 29, 20, 30);
        boolean sendable = timeSmsFilter.isSendable(testDt);
        Assertions.assertThat(sendable).isFalse();
    }

}