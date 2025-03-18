package com.jisutudy.repository;

import com.jisutudy.domain.Placeholder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaceholderRepository extends JpaRepository<Placeholder, Long> {
}
