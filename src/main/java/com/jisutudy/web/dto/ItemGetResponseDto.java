package com.jisutudy.web.dto;

import com.jisutudy.domain.performance.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ItemGetResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public ItemGetResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
    }
}
