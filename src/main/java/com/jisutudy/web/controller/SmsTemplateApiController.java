package com.jisutudy.web.controller;

import com.jisutudy.service.SmsTemplateService;
import com.jisutudy.web.dto.SmsTemplateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/smsTemplate")
@RequiredArgsConstructor
public class SmsTemplateApiController {

    private final SmsTemplateService smsTemplateService;

    @PostMapping
    public Long create(@RequestBody SmsTemplateRequestDto requestDto) {
        return smsTemplateService.create(requestDto);
    }
}
