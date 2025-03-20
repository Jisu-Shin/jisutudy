package com.jisutudy.web.controller;

import com.jisutudy.service.CustService;
import com.jisutudy.web.dto.CustSaveRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/new")
    public String save(@Valid CustSaveRequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "cust-createForm";
        }
        custService.save(requestDto);
        return "redirect:/custs";
    }

    @GetMapping("/save")
    public String saveCust() {
        return "cust-save";
    }
}
