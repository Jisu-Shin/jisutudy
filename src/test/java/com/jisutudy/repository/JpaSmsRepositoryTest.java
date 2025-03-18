package com.jisutudy.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaSmsRepositoryTest {

    @Autowired
    JpaSmsRepository jpaSmsRepository;

    @Test
    void sms발송() {
    }

    @Test
    void findSmsListBySendDt() {
    }

    @Test
    void findAllBySendDtBetween() {
    }
}