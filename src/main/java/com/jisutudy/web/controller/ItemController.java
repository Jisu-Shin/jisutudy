package com.jisutudy.web.controller;

import com.jisutudy.domain.performance.Concert;
import com.jisutudy.service.ItemService;
import com.jisutudy.web.dto.ItemCreateRequestDto;
import com.jisutudy.web.dto.ItemGetResponseDto;
import com.jisutudy.web.dto.ItemUpdateRequestDto;
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

    @GetMapping("/new")
    public String createItemForm() {
        return "item-createForm";
    }

    @PostMapping("/new")
    public String createItem(ItemCreateRequestDto requestDto) {
        Concert concert = requestDto.toEntity();
        itemService.saveItem(concert);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String updateItemForm(@PathVariable("id")Long id, Model model) {
        ItemGetResponseDto findItem = itemService.findById(id);
        model.addAttribute("item", findItem);
        return "item-updateForm";
    }

    @PostMapping("/{id}")
    public String updateItem(ItemUpdateRequestDto requestDto) {
        itemService.updateItem(requestDto);
        return "redirect:/items";
    }

}
