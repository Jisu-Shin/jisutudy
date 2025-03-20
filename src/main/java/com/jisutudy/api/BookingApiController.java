package com.jisutudy.api;

import com.jisutudy.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingApiController {

    private final BookingService bookingService;

    @PostMapping("/{id}/cancel")
    public Long cancelBooking(@PathVariable("id")Long id) {
        return bookingService.cancelBooking(id);
    }
}
