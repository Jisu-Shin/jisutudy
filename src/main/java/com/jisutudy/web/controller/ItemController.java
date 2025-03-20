package com.jisutudy.web.controller;

import com.jisutudy.domain.performance.Concert;
import com.jisutudy.service.ItemService;
import com.jisutudy.web.dto.ItemCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("")
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

    @GetMapping()
    public String updateItem(@PathVariable("itemId")Long itemId) {
        return String.valueOf("");
    }

}
