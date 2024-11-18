package com.jisutudy.web;


import com.jisutudy.service.JpaSmsService;
import com.jisutudy.web.dto.SmsFindListResponseDto;
import com.jisutudy.web.dto.SmsSendRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SmsApiController {

    private final JpaSmsService jpaSmsService;

    @PostMapping("/api/v1/sms/send")
    public Long send(@RequestBody SmsSendRequestDto requestDto) {
        return jpaSmsService.send(requestDto);
    }

    @GetMapping("api/v1/sms/sendList")
    public List<SmsFindListResponseDto> findList(@RequestParam("startDt") String startDt
            , @RequestParam("endDt") String endDt) {
        return jpaSmsService.findSmsList(startDt, endDt);
    }

}
