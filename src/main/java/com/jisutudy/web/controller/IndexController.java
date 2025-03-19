package com.jisutudy.web.controller;

import com.jisutudy.domain.performance.Concert;
import com.jisutudy.service.CustService;
import com.jisutudy.service.ItemService;
import com.jisutudy.service.SmsService;
import com.jisutudy.service.SmsTemplateService;
import com.jisutudy.web.dto.ItemCreateRequestDto;
import com.jisutudy.web.dto.SmsTemplateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final CustService custService;
    private final SmsService smsService;
    private final SmsTemplateService smsTemplateService;
    private final ItemService itemService;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/sms/send")
    public String sendSms(Model model) {
        model.addAttribute("custs",custService.findAll());
        model.addAttribute("templates", smsTemplateService.findAll());
        return "sms-sendForm";
    }

    @GetMapping("/sms/sendList")
    public String getSmsList(Model model) {
        System.out.println("sms목록조회");
        LocalDate startDt = LocalDate.now();
        String parseStatDt = startDt.format(DateTimeFormatter.ofPattern("yyyyMMdd")).concat("0000");

        LocalDate endDt = LocalDate.now().plusDays(1);
        String parseEndDt = endDt.format(DateTimeFormatter.ofPattern("yyyyMMdd")).concat("0000");

        model.addAttribute("sms", smsService.findSmsList(parseStatDt, parseEndDt));
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
    public String getCustList(Model model) {
        model.addAttribute("custs",custService.findAll());
        return "cust-findAll";
    }

    @GetMapping("/templates/new")
    public String createTemplateForm(Model model) {
        model.addAttribute("templates", smsTemplateService.findAll());
        return "template-create";
    }

    @PostMapping("/templates/new")
    public String createTemplate(SmsTemplateRequestDto requestDto) {
        smsTemplateService.create(requestDto);
        return "redirect:/templates/new";
    }

    @GetMapping("/items")
    public String getItemList(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "item-getList";
    }

    @GetMapping("/items/new")
    public String createItemForm() {
        return "item-createForm";
    }

    @PostMapping("items/new")
    public String createItem(ItemCreateRequestDto requestDto) {
        System.out.println(requestDto);
        Concert concert = requestDto.toEntity();
        itemService.saveItem(concert);
        return "redirect:/";
    }
}
