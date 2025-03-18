package com.jisutudy.repository;

import com.jisutudy.domain.Booking;

import java.util.List;

public interface BookingQueryDsl {
    public List<Booking> findAll(BookingSearch bookingSearch);
}
