package com.jisutudy.web.controller;

import com.jisutudy.service.CustService;
import com.jisutudy.service.SmsService;
import com.jisutudy.service.SmsTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;
    private final CustService custService;
    private final SmsTemplateService smsTemplateService;

    @GetMapping("/send")
    public String sendSms(Model model) {
        model.addAttribute("custs",custService.findAll());
        model.addAttribute("templates", smsTemplateService.findAll());
        return "sms-sendForm";
    }

    @GetMapping("/sendList")
    public String getSmsList(Model model) {
        LocalDate startDt = LocalDate.now().minusDays(6);
        String parseStatDt = startDt.format(DateTimeFormatter.ofPattern("yyyyMMdd")).concat("0000");

        LocalDate endDt = LocalDate.now().plusDays(1);
        String parseEndDt = endDt.format(DateTimeFormatter.ofPattern("yyyyMMdd")).concat("0000");

        model.addAttribute("sms", smsService.findSmsList(parseStatDt, parseEndDt));
        return "sms-sendlist";
    }
}
