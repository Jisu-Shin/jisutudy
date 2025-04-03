package com.jisutudy.repository;

import com.jisutudy.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookingRepository extends JpaRepository<Booking, Long>, BookingQueryDsl {
}
