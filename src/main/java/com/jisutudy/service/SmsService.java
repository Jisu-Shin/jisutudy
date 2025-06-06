package com.jisutudy.service;

import com.jisutudy.repository.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.dto.SmsFindListResponseDto;
import com.jisutudy.dto.SmsSendRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SmsService {
    private final JpaSmsRepository jpaSmsRepository;
    private final SmsFactory smsFactory;

    @Transactional
    public boolean send(SmsSendRequestDto requestDto) {
        log.info("@@@@@ SmsService.send requestDto {}", requestDto.toString());

        validateRequest(requestDto);

        List<Sms> smsList = smsFactory.createSmsList(requestDto);
        log.info("@@@@@ smsFactory 완료");

        jpaSmsRepository.saveAll(smsList);
        log.info("@@@@@ repository saveAll 완료");

        return true;
    }

    private void validateRequest(SmsSendRequestDto requestDto) {
        if(requestDto.getCustIdList().isEmpty()) {
            throw new IllegalArgumentException("sms 발송할 고객이 없습니다.");
        }
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
