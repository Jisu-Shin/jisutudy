package com.jisutudy.repository;

import com.jisutudy.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBookingRepository extends JpaRepository<Booking, Long>, BookingQueryDsl {
}
