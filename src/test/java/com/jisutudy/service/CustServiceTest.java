package com.jisutudy.service;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.repository.JpaCustRepository;
import com.jisutudy.web.dto.CustSaveRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustServiceTest {

    @Autowired
    CustService custService;

    @Autowired
    JpaCustRepository jpaCustRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        CustSaveRequestDto custSaveRequestDto = new CustSaveRequestDto("kim","01012345678",CustSmsConsentType.ALL_ALLOW.getLabel());

        //when
        Long saveId = custService.save(custSaveRequestDto);

        //then
        Optional<Cust> cust = jpaCustRepository.findById(saveId);
        assertEquals(custSaveRequestDto.getName(), cust.get().getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        CustSaveRequestDto cust = new CustSaveRequestDto("kim1","1234",CustSmsConsentType.ALL_ALLOW.getLabel());
        CustSaveRequestDto cust2 = new CustSaveRequestDto("kim1","4567",CustSmsConsentType.ALL_ALLOW.getLabel());

        //when
        custService.save(cust);
        assertThrows(IllegalStateException.class, () -> custService.save(cust2));
    }

}