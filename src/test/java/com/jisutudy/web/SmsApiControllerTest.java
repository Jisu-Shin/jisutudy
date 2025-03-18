package com.jisutudy.web;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.repository.JpaCustRepository;
import com.jisutudy.repository.JpaSmsRepository;
import com.jisutudy.web.dto.SmsSendRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    static Long custId;

    @BeforeEach
    public void makeCust() {
        jpaCustRepository.save(Cust.builder()
                .name("신지수")
                .phoneNumber("01012345678")
                .smsConsentType(CustSmsConsentType.ALL_ALLOW)
                .build());

        Cust c = jpaCustRepository.findAll().get(0);
        custId = c.getId();

    }

    @Test
    void sms발송(){
        //given
        SmsSendRequestDto requestDto = SmsSendRequestDto.builder()
                                        .custIdList(List.of(custId))
                                        .smsContent("메시지발송")
                                        .smsType("01") // SmsType.INFORMAITONAL
                                        .sendDt("202411181930")
                                        .build();

        String url = "http://localhost:"+port+"/api/sms/send";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto, Long.class);
        System.out.println("responseEntity = " + responseEntity);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    void sms목록확인() {
        //given
        sms발송();
        String startDt = "202411180000";
        String endDt = "202411200000";

        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/api/sms/sendList")
                .queryParam("startDt", startDt)
                .queryParam("endDt", endDt)
                .toUriString();

        System.out.println(url);

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