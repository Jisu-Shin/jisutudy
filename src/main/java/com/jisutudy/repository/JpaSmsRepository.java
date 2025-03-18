package com.jisutudy.repository;

import com.jisutudy.domain.sms.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaSmsRepository extends JpaRepository<Sms, Long>, SmsQueryDsl{
    List<Sms> findByCustId(Long custId);

    List<Sms> findAllBySendDtBetween(LocalDateTime startDt, LocalDateTime endDt);
}
