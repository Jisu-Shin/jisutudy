package com.jisutudy.service;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.repository.JpaCustRepository;
import com.jisutudy.web.dto.CustListResponseDto;
import com.jisutudy.web.dto.CustSaveRequestDto;
import com.jisutudy.web.dto.CustUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CustService {

    private final JpaCustRepository custRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long save(CustSaveRequestDto requestDto) {
        // 중복 회원 검증
        validateDuplicateCust(requestDto);
        return custRepository.save(requestDto.toEntity()).getId();
    }

    private void validateDuplicateCust(CustSaveRequestDto requestDto) {
        // EXCEPTION
        Optional<Cust> findCust = custRepository.findByName(requestDto.getName());
        if (!findCust.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 고객입니다.");
        }
    }

    @Transactional
    public Long update(Long id, CustUpdateRequestDto requestDto) {
        Cust cust = custRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다. id = " + id));
        cust.update(requestDto.getPhoneNumber(), CustSmsConsentType.of(requestDto.getSmsConsentType()));
        return id;
    }

    public CustListResponseDto findById(Long id) {
        Cust entity = custRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 고객이 없습니다. id = " + id));

        return new CustListResponseDto(entity);
    }

    public CustListResponseDto findByPhoneNumber(String phoneNumber) {
        String subStringPhoneNumber = phoneNumber.substring(phoneNumber.length()-4);
        Cust entity = custRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()->new IllegalArgumentException("해당 고객이 없습니다. 전화번호 = " + subStringPhoneNumber));
        return new CustListResponseDto(entity);
    }

    /**
     * 회원 전체 조회
     */
    public List<CustListResponseDto> findAll() {
        return custRepository.findAll().stream()
                .map(CustListResponseDto::new)
                .collect(Collectors.toList());
    }
}
