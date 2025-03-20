package com.jisutudy.web.controller;

import com.jisutudy.service.CustService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/custs")
public class CustController {

    private final CustService custService;

    @GetMapping("")
    public String getCustList(Model model) {
        model.addAttribute("custs",custService.findAll());
        return "cust-findAll";
    }

    @GetMapping("/new")
    public String createCust() {
        return "cust-createForm";
    }

    @GetMapping("/save")
    public String saveCust() {
        return "cust-save";
    }
}
