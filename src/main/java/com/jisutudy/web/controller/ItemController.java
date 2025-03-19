package com.jisutudy.web.controller;

import com.jisutudy.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    // 아이템수정
    @GetMapping()
    public String updateItem(@PathVariable("itemId")Long itemId) {

        return String.valueOf("");
    }

}
