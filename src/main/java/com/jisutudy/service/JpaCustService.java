package com.jisutudy.service;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.customer.JpaCustRepository;
import com.jisutudy.web.dto.CustResponseDto;
import com.jisutudy.web.dto.CustSaveRequestDto;
import com.jisutudy.web.dto.CustUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaCustService {
    private final JpaCustRepository custRepository;

    @Transactional
    public Long save(CustSaveRequestDto requestDto) {
        return custRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CustUpdateRequestDto requestDto) {
        Cust cust = custRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다. id = " + id));
        cust.update(requestDto.getPhoneNumber(), CustSmsConsentType.of(requestDto.getSmsConsentType()));
        return id;
    }

    public CustResponseDto findById(Long id) {
        Cust entity = custRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 고객이 없습니다. id = " + id));

        return new CustResponseDto(entity);
    }

    public CustResponseDto findByPhoneNumber(String phoneNumber) {
        String subStringPhoneNumber = phoneNumber.substring(phoneNumber.length()-4);
        Cust entity = custRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()->new IllegalArgumentException("해당 고객이 없습니다. 전화번호 = " + subStringPhoneNumber));
        return new CustResponseDto(entity);
    }
}
