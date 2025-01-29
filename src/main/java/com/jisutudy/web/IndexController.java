package com.jisutudy.web;

import com.jisutudy.service.JpaCustService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final JpaCustService custService;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/sms/send")
    public String sendSms() {
        return "sms-send";
    }

    @GetMapping("/sms/sendList")
    public String findAllSmsList() {
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
