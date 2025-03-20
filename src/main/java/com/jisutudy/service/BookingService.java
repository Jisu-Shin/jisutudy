package com.jisutudy.service;

import com.jisutudy.domain.Booking;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.performance.Item;
import com.jisutudy.repository.BookingSearch;
import com.jisutudy.repository.JpaBookingRepository;
import com.jisutudy.repository.JpaCustRepository;
import com.jisutudy.repository.JpaItemRepository;
import com.jisutudy.web.dto.BookingListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingService {

    private final JpaBookingRepository jpaBookingRepository;
    private final JpaCustRepository jpaCustRepository;
    private final JpaItemRepository jpaItemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long book(Long custId, Long itemId, int count) {

        //엔티티조회
        Cust cust = jpaCustRepository.findById(custId)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다: " + custId));
        Item item = jpaItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 없습니다: " + itemId));

        //예약 생성
        Booking booking = Booking.createBooking(cust, item, count);

        //예약 저장
        jpaBookingRepository.save(booking);

        return booking.getId();
    }

    /**
     * 예약 취소
     */
    @Transactional
    public Long cancelBooking(Long bookingId) {
        //예약 엔티티 조회
        Booking booking = jpaBookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다: " + bookingId));

        // 예약 취소
        booking.cancel();
        return bookingId;
    }

    /**
     * 검색
     */
    public List<BookingListResponseDto> findBooking(BookingSearch bookingSearch) {
        return jpaBookingRepository.findAll(bookingSearch).stream()
                .map(BookingListResponseDto::new)
                .collect(Collectors.toList());
    }
}
