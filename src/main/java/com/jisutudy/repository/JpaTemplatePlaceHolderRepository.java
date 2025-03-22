package com.jisutudy.repository;

import com.jisutudy.domain.SmsTemplateVariableRel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTemplatePlaceHolderRepository extends JpaRepository<SmsTemplateVariableRel, Long> {
}
