package com.jisutudy.domain.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaCustRepositoryTest {

    @Autowired
    JpaCustRepository custRepository;

    @AfterEach
    public void cleanup() {
        custRepository.deleteAll();
    }

    @Test
    public void 게시글저장불러오기() {
        //given
        String name = "홍길동";
        String phoneNumber = "01012345678";
        CustSmsConsentType type = CustSmsConsentType.ALL_ALLOW;

        custRepository.save(Cust.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .smsConsentType(type)
                .build());

        //when
        List<Cust> custList = custRepository.findAll();

        //then
        Cust cust = custList.get(0);
        assertThat(cust.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(cust.getSmsConsentType()).isEqualTo(type);

    }

    @Test
    public void BaseTimeEntity등록() {
        //givne
        LocalDateTime now = LocalDateTime.now();
        custRepository.save(Cust.builder()
                .name("홍길동")
                .phoneNumber("01023456789")
                .smsConsentType(CustSmsConsentType.ALL_ALLOW)
                .build());

        //when
        Cust cust = custRepository.findAll().get(0);

        //then
        System.out.println(">>>>>>>>> createDate= "+cust.getCreatedDate()+ ", modifiedDate= " + cust.getModifiedDate());

        assertThat(cust.getCreatedDate()).isAfter(now);
        assertThat(cust.getModifiedDate()).isAfter(now);
    }
}