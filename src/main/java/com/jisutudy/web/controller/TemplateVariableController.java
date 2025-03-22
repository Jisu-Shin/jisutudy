package com.jisutudy.web.controller;

import com.jisutudy.service.TemplateVariableService;
import com.jisutudy.web.dto.TemplateVariableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/templateVariable")
public class TemplateVariableController {

    private final TemplateVariableService templateVariableService;

    @PostMapping("/new")
    public String create(TemplateVariableDto requestDto) {
        templateVariableService.createPlaceholder(requestDto);
        return "redirect:/smsTemplates/new";
    }

}
