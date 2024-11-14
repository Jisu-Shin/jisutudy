package com.jisutudy.service;

import com.jisutudy.domain.sms.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.web.dto.SmsSendRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JpaSmsService {
    private final JpaSmsRepository jpaSmsRepository;

    @Transactional
    public Long send(SmsSendRequestDto requestDto) {
        return jpaSmsRepository.save(requestDto.toEntity()).getSmsId();
    }
//
//    public List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt) {
//        return jpaSmsRepository.
//    }

}
