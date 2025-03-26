package com.jisutudy.web.dto;

import com.jisutudy.domain.Booking;
import com.jisutudy.domain.BookingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor
public class BookingListResponseDto {

    private Long bookId;
    private Long custId;
    private String custName;
    private String itemName;
    private int count;
    private String bookingStatus;
    private String bookDt;
    private boolean isBooking;

    public BookingListResponseDto(Booking booking) {
        this.bookId = booking.getId();
        this.custId = booking.getCust().getId();
        this.custName = booking.getCust().getName();
        this.itemName = booking.getItem().getName();
        this.count = booking.getCount();
        this.bookingStatus = booking.getStatus().getDisplayName();
        this.bookDt = booking.getBookDt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        this.isBooking = booking.getStatus() == BookingStatus.BOOK;
    }
}
