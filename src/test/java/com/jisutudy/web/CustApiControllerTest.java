package com.jisutudy.web;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.customer.JpaCustRepository;
import com.jisutudy.web.dto.CustResponseDto;
import com.jisutudy.web.dto.CustSaveRequestDto;
import com.jisutudy.web.dto.CustUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JpaCustRepository jpaCustRepository;

    @AfterEach
    public void tearDown() throws Exception{
        jpaCustRepository.deleteAll();
    }

    @Test
    public void cust등록된다() throws Exception{
        //given
        String name = "신지수";
        String phoneNumber = "01012345678";
        String consetType = "01";
        CustSaveRequestDto requestDto = CustSaveRequestDto.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .smsConsentType(consetType)
                .build();

        String url = "http://localhost:" + port + "/api/custs";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Cust> all = jpaCustRepository.findAll();
        Cust sample = all.get(0);
        assertThat(sample.getName()).isEqualTo(name);
        assertThat(sample.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(sample.getSmsConsentType()).isEqualTo(CustSmsConsentType.of(consetType));
    }

    @Test
    public void cust수정된다() throws Exception {
        //given
        Cust saveCust = jpaCustRepository.save(Cust.builder()
                .name("신지수")
                .phoneNumber("01012345678")
                .smsConsentType(CustSmsConsentType.ALL_ALLOW)
                .build());

        Long updateId = saveCust.getId();
        String expectPhoneNumber = "01098765432";
        CustSmsConsentType expectType = CustSmsConsentType.ALL_DENY;

        CustUpdateRequestDto requestDto = CustUpdateRequestDto.builder()
                .phoneNumber(expectPhoneNumber)
                .smsConsentType(expectType.getLabel())
                .build();

        String url = "http://localhost:" + port + "/api/custs/" + updateId;

        HttpEntity<CustUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        System.out.println("responseEntitiy Body" + responseEntity.getBody());

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Cust cust = jpaCustRepository.findAll().get(0);
        assertThat(cust.getPhoneNumber()).isEqualTo(expectPhoneNumber);
        assertThat(cust.getSmsConsentType()).isEqualTo(expectType);

    }

    @Test
    public void cust조회한다() throws Exception {
        //given
        cust등록된다();
        Cust c = jpaCustRepository.findAll().get(0);
        Long findId = c.getId();

        String url = "http://localhost:" + port + "/api/custs/" + findId;

        //when
        ResponseEntity<CustResponseDto> responseEntity = restTemplate.getForEntity(url, CustResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody());

        assertThat(responseEntity.getBody().getName()).isEqualTo("신지수");
        assertThat(responseEntity.getBody().getPhoneNumber()).isEqualTo("01012345678");
        assertThat(responseEntity.getBody().getSmsConsentType()).isEqualTo(CustSmsConsentType.ALL_ALLOW.getLabel());

    }

    @Test
    public void cust전화번호로조회() throws Exception {
        //given
        cust등록된다();
        Cust c = jpaCustRepository.findAll().get(0);
        String findByPhoneNumber = c.getPhoneNumber();

        String url = "http://localhost:" + port + "/api/custs/search";

        //when
        ResponseEntity<CustResponseDto> responseEntity = restTemplate.postForEntity(url, findByPhoneNumber, CustResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody());

        assertThat(responseEntity.getBody().getName()).isEqualTo("신지수");
        assertThat(responseEntity.getBody().getPhoneNumber()).isEqualTo("01012345678");
        assertThat(responseEntity.getBody().getSmsConsentType()).isEqualTo(CustSmsConsentType.ALL_ALLOW.getLabel());
    }

}