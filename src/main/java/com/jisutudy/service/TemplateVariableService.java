package com.jisutudy.service;

import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import com.jisutudy.dto.TemplateVariableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TemplateVariableService {

    private final JpaTemplateVariableRepository jpaTemplateVariableRepository;

    @Transactional
    public Long create(TemplateVariableDto dto) {
        TemplateVariable templateVariable = TemplateVariable.create(dto.getEnText(), dto.getKoText(), dto.getVariableType());
        jpaTemplateVariableRepository.save(templateVariable);

        return templateVariable.getId();
    }

    public List<TemplateVariableDto> findAll() {
        return jpaTemplateVariableRepository.findAll().stream()
                .map(TemplateVariableDto::new)
                .collect(Collectors.toList());
    }
}
