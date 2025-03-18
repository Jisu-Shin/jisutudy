package com.jisutudy.web;

import com.jisutudy.service.CustService;
import com.jisutudy.service.SmsService;
import com.jisutudy.service.SmsTemplateService;
import com.jisutudy.web.dto.CustSaveRequestDto;
import com.jisutudy.web.dto.SmsTemplateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final CustService custService;
    private final SmsService smsService;
    private final SmsTemplateService smsTemplateService;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/sms/send")
    public String sendSms() {
        return "sms-send";
    }

    @GetMapping("/sms/sendlist")
    public String findAllSmsList(Model model) {
        LocalDate endDt = LocalDate.now().plusDays(1);
        String parseEndDt = endDt.format(DateTimeFormatter.ofPattern("yyyyMMdd")).concat("0000");
        model.addAttribute("sms", smsService.findSmsList("202412010000", parseEndDt));
        return "sms-sendlist";
    }

    @GetMapping("/cust/save")
    public String saveCust() {
        return "cust-save";
    }

    @GetMapping("/custs/new")
    public String createCust() {
        return "cust-createForm";
    }

    @GetMapping("/custs")
    public String findAllCust(Model model) {
        model.addAttribute("custs",custService.findAll());
        return "cust-findAll";
    }

    @GetMapping("/templates/new")
    public String getTemplates(Model model) {
        model.addAttribute("templates", smsTemplateService.findAll());
        return "template-create";
    }

    @PostMapping("/templates/new")
    public String createTemplate(SmsTemplateRequestDto requestDto) {
        System.out.println(requestDto);
        smsTemplateService.create(requestDto);
        return "redirect:/templates/new";
    }
}
