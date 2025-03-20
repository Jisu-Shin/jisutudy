package com.jisutudy.web.controller;

import com.jisutudy.domain.performance.Item;
import com.jisutudy.repository.BookingSearch;
import com.jisutudy.service.BookingService;
import com.jisutudy.service.CustService;
import com.jisutudy.service.ItemService;
import com.jisutudy.web.dto.BookingCreateRequestDto;
import com.jisutudy.web.dto.BookingListResponseDto;
import com.jisutudy.web.dto.CustListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final CustService custService;
    private final BookingService bookingService;
    private final ItemService itemService;

    @GetMapping("")
    public String bookingList(Model model, @ModelAttribute("bookingSearch") BookingSearch bookingSearch) {
        if (bookingSearch.getCustName() == null) {
            bookingSearch.setCustName("");
        }

        List<BookingListResponseDto> bookings = bookingService.findBooking(bookingSearch);
        model.addAttribute("bookings", bookings);
        model.addAttribute("bookingSearch", bookingSearch);
        return "booking-getList";
    }

    @GetMapping("/new")
    public String createBookingForm(Model model) {
        List<CustListResponseDto> custs = custService.findAll();
        List<Item> items = itemService.findAll();

        model.addAttribute("custs", custs);
        model.addAttribute("items", items);

        return "booking-createForm";
    }

    @PostMapping("/new")
    public String booking(BookingCreateRequestDto requestDto) {
        bookingService.book(requestDto.getCustId(), requestDto.getItemId(), requestDto.getCount());

        return "redirect:/";
    }
}
