package com.jisutudy.repository;

import com.jisutudy.domain.BookingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookingSearch {
    private String custName; // 고객명
    private BookingStatus bookingStatus; // 예약상태 [BOOK, CANCEL]
    private Long itemId;
}
