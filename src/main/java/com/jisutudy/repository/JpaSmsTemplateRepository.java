package com.jisutudy.repository;

import com.jisutudy.domain.SmsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSmsTemplateRepository extends JpaRepository<SmsTemplate, Long> {
}
