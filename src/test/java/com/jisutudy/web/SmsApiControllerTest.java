package com.jisutudy.web;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.repository.JpaCustRepository;
import com.jisutudy.repository.JpaSmsRepository;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.web.dto.SmsSendRequestDto;
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

    @Autowired
    private JpaSmsTemplateRepository jpaSmsTemplateRepository;

    private Long custId;
    private Long smsTemplateId;

    @BeforeEach
    void setUp() {
        Cust cust = createCust();
        SmsTemplate smsTemplate = createSmsTemplate();

        custId = cust.getId();
        smsTemplateId = smsTemplate.getId();
    }

    @Test
    void sms발송(){
        //given
        SmsSendRequestDto requestDto = SmsSendRequestDto.builder()
                                        .custIdList(List.of(custId))
                                        .templateId(smsTemplateId)
                                        .sendDt("202411181930")
                                        .build();

        String url = "http://localhost:"+port+"/api/sms/send";

        //when
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url,requestDto, Boolean.class);
        System.out.println("responseEntity = " + responseEntity);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    private SmsTemplate createSmsTemplate() {
        SmsTemplate smsTemplate = SmsTemplate.createSmsTemplate("문자테스트발송", SmsType.INFORMAITONAL);
        jpaSmsTemplateRepository.save(smsTemplate);
        return smsTemplate;
    }

    private Cust createCust() {
        Cust cust = Cust.createCust("테스트고객","01012345678",CustSmsConsentType.ALL_ALLOW);
        jpaCustRepository.save(cust);
        return cust;
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
    
    @Test
    public void sms발송유효성검사() throws Exception {
        //given
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/api/sms/send")
                .toUriString();

        SmsSendRequestDto smsSendRequestDto = SmsSendRequestDto.builder()
                .custIdList(List.of(1L))
                .sendDt("202504042030")
                .build();

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, smsSendRequestDto, String.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.out.println(responseEntity.getBody());
    }

}