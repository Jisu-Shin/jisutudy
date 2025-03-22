package com.jisutudy.repository;

import com.jisutudy.domain.TemplateVariable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaceholderRepository extends JpaRepository<TemplateVariable, Long> {
}
