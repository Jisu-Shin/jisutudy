package com.jisutudy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/sms/send")
    public String sendSms() {
        return "sms-send";
    }

    @GetMapping("/cust/save")
    public String saveCust() {
        return "cust-save";
    }
}
