package com.jisutudy.domain.sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaSmsRepository extends JpaRepository<Sms, Long> {
    List<Sms> findByCustId(Long custId);
//
//    @Query("SELECT")
//    List<Sms> findBySendDt();
}
