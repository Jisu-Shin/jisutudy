package com.jisutudy.repository;

import com.jisutudy.domain.TemplatePlaceholder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTemplatePlaceHolderRepository extends JpaRepository<TemplatePlaceholder, Long> {
}
