package com.jisutudy.repository;

import com.jisutudy.domain.SmsTmpltVarRel;
import com.jisutudy.domain.SmsTmpltVarRelId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSmsTemplateVarRelRepository extends JpaRepository<SmsTmpltVarRel, SmsTmpltVarRelId> {
    List<SmsTmpltVarRel> findBySmsTmpltVarRelId_SmsTmpltId(Long smsTmpltId);
}
