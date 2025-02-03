package com.jisutudy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지로딩() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("Jisutudy");
    }

    @Test
    void 메시지목록확인() {
        //when
        StringBuilder urlSb = new StringBuilder("/sms/sendList");
        urlSb.append("?startDt=").append("202402010000");
        urlSb.append("&endDt=").append("202402032359");


        String body = this.restTemplate.getForObject(urlSb.toString(), String.class);


        //then
        System.out.println(body);
    }
}