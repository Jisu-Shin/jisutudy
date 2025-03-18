package com.jisutudy.service;

import com.jisutudy.domain.performance.Concert;
import com.jisutudy.domain.performance.Item;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
    public void 아이템추가() {
    }

}