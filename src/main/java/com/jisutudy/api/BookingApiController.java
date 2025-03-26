package com.jisutudy.api;

import com.jisutudy.repository.BookingSearch;
import com.jisutudy.service.BookingService;
import com.jisutudy.web.dto.BookingListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingApiController {

    private final BookingService bookingService;

    @PostMapping("/{id}/cancel")
    public Long cancelBooking(@PathVariable("id")Long id) {
        return bookingService.cancelBooking(id);
    }

    @PostMapping("/search")
    public List<BookingListResponseDto> searchBooking(@RequestBody BookingSearch bookingSearch) {
        return bookingService.findBooking(bookingSearch);
    }
}
