package com.jisutudy.web;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.customer.JpaCustRepository;
import com.jisutudy.domain.sms.JpaSmsRepository;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.web.dto.SmsFindListResponseDto;
import com.jisutudy.web.dto.SmsSendRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JpaSmsRepository jpaSmsRepository;

    @Autowired
    private JpaCustRepository jpaCustRepository;

    @AfterEach
    public void tearDown() throws Exception {
        jpaSmsRepository.deleteAll();
    }

    @BeforeEach
    public void makeCust() {
        jpaCustRepository.save(Cust.builder()
                .name("신지수")
                .phoneNumber("01012345678")
                .smsConsentType(CustSmsConsentType.ALL_ALLOW)
                .build());

    }

    @Test
    void sms발송(){
        //given
        LocalDateTime ldt = LocalDateTime.of(2024,11,18,19,30);
        SmsSendRequestDto requestDto = SmsSendRequestDto.builder()
                                        .custId(1L)
                                        .smsContent("메시지발송")
                                        .smsType(SmsType.INFORMAITONAL)
                                        .sendDt(ldt)
                                        .build();

        String url = "http://localhost:"+port+"/api/v1/sms/send";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto, Long.class);
        System.out.println("responseEntity = " + responseEntity);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    void sms목록확인() {
        //given
        sms발송();
        String startDt = "2411180000";
        String endDt = "2411200000";

        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/api/v1/sms/sendList")
                .queryParam("startDt", startDt)
                .queryParam("endDt", endDt)
                .toUriString();

        //when
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                String.class
        );

//        ResponseEntity<List<SmsFindListResponseDto>> responseEntity = restTemplate.exchange(url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<SmsFindListResponseDto>>() {}
//        );
        System.out.println("responseEntity = " + responseEntity);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

}