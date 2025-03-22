package com.jisutudy.web.controller;

import com.jisutudy.service.TemplateVariableService;
import com.jisutudy.service.SmsTemplateService;
import com.jisutudy.web.dto.SmsTemplateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/smsTemplates")
public class SmsTemplateController {

    private final SmsTemplateService smsTemplateService;
    private final TemplateVariableService templateVariableService;

    @GetMapping("/new")
    public String createTemplateForm(Model model) {
        model.addAttribute("templates", smsTemplateService.findAll());
        model.addAttribute("placeholders", templateVariableService.findAll());
        return "template-create";
    }

    @PostMapping("/new")
    public String createTemplate(SmsTemplateRequestDto requestDto) {
        smsTemplateService.create(requestDto);
        return "redirect:/smsTemplates/new";
    }

}
