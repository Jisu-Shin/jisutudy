package com.jisutudy.service;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.JpaCustRepository;
import com.jisutudy.domain.sms.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.filter.SmsFilter;
import com.jisutudy.web.dto.SmsFindListResponseDto;
import com.jisutudy.web.dto.SmsSendRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JpaSmsService {
    private final JpaSmsRepository jpaSmsRepository;
    private final JpaCustRepository jpaCustRepository;
    private final SmsFilter smsFilter;

    @Transactional
    public Long send(SmsSendRequestDto requestDto) {
        Sms sms = requestDto.toEntity();

        // 고객 정보 세팅
        // TODO 해당 고객이 없을 경우 처리는 ?
        Optional<Cust> cust = jpaCustRepository.findById(sms.getCustId());
        sms.setCustPhoneNumber(cust.get().getPhoneNumber());

        // 필터링
        SmsResult smsResult = smsFilter.filter(sms,cust.get());

        // sms 처리결과를 세팅
        sms.setSmsResult(smsResult);

        return jpaSmsRepository.save(sms).getSmsId();
    }

    /**
     * sms 발송 목록을 조회하는 서비스
     *
     * @param startDt yyyyMMddHHmm 형식
     * @param endDt yyyyMMddHHmm 형식
     */
    @Transactional
    public List<SmsFindListResponseDto> findSmsList(String startDt, String endDt) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime startLdt = LocalDateTime.parse(startDt,format);
        LocalDateTime endLdt = LocalDateTime.parse(endDt,format);
        return jpaSmsRepository.findAllBySendDtBetween(startLdt, endLdt).stream()
                .map(SmsFindListResponseDto::new)
                .collect(Collectors.toList());
    }
}
