package com.jisutudy.client;

import com.jisutudy.dto.CustListResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class CustApiServiceTest {

    @Autowired CustApiService custApiService;

    @Test
    public void 고객서비스연결() throws Exception {
        //given
        CustListResponseDto cust = custApiService.getCust(1L);

        //when

        //then
    }

}