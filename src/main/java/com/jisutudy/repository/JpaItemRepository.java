package com.jisutudy.repository;

import com.jisutudy.domain.Booking;
import com.jisutudy.domain.performance.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaItemRepository extends JpaRepository<Item, Long> {
    List<Booking> findByNameLike(String search);
}
