package com.jisutudy.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookingCreateRequestDto {
    private long custId;
    private long itemId;
    private int count;

}
