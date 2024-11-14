package com.jisutudy.web;

import com.jisutudy.service.JpaCustService;
import com.jisutudy.web.dto.CustResponseDto;
import com.jisutudy.web.dto.CustSaveRequestDto;
import com.jisutudy.web.dto.CustUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

@RequiredArgsConstructor
@RestController
public class CustApiController {

    private final JpaCustService jpaCustService;

    @PostMapping("/api/v1/cust")
    public Long save(@RequestBody CustSaveRequestDto requestDto) {
        return jpaCustService.save(requestDto);
    }

    @PutMapping("/api/v1/cust/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody CustUpdateRequestDto requestDto) {
        return jpaCustService.update(id, requestDto);
    }

    @GetMapping("/api/v1/cust/{id}")
    public CustResponseDto findById(@PathVariable("id") Long id) {
        return jpaCustService.findById(id);
    }

    @PostMapping("/api/v1/cust/byPhoneNumber")
    public CustResponseDto findByPhoneNumber(@RequestBody String phoneNumber) {
        return jpaCustService.findByPhoneNumber(phoneNumber);
    }

}
