package com.jisutudy.repository;

import com.jisutudy.domain.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingSearch {

    private String custName; // 고객명
    private BookingStatus bookingStatus; // 예약상태 [BOOK, CANCEL]
}
