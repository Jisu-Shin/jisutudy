package com.jisutudy.web;


import com.jisutudy.service.JpaSmsService;
import com.jisutudy.web.dto.SmsFindListResponseDto;
import com.jisutudy.web.dto.SmsSendRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="SmsApiController", description = "sms 관련 rest api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sms")
public class SmsApiController {

    private final JpaSmsService jpaSmsService;

    @Operation(summary="sms 발송")
    @PostMapping("/send")
    public Long send(@RequestBody SmsSendRequestDto requestDto) {
        return jpaSmsService.send(requestDto);
    }

    @Operation(summary="sms 발송목록 조회")
    @GetMapping("/sendList")
    public List<SmsFindListResponseDto> findList(@RequestParam("startDt") String startDt
            , @RequestParam("endDt") String endDt) {
        return jpaSmsService.findSmsList(startDt, endDt);
    }

}
