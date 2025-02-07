package com.jisutudy.web;

import com.jisutudy.service.JpaCustService;
import com.jisutudy.service.JpaSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final JpaCustService custService;
    private final JpaSmsService smsService;

    @GetMapping
    public String index(){
        return "index";
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

    @GetMapping("/cust/findAll")
    public String findAllCust(Model model) {
        model.addAttribute("custs",custService.findAll());
        return "cust-findAll";
    }
}
