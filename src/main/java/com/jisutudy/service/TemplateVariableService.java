package com.jisutudy.service;

import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.repository.JpaPlaceholderRepository;
import com.jisutudy.web.dto.TemplateVariableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TemplateVariableService {

    private final JpaPlaceholderRepository jpaPlaceholderRepository;

    @Transactional
    public Long createPlaceholder(TemplateVariableDto dto) {
        TemplateVariable templateVariable = TemplateVariable.createPlaceholder(dto);
        jpaPlaceholderRepository.save(templateVariable);

        return templateVariable.getId();
    }

    public List<TemplateVariableDto> findAll() {
        return jpaPlaceholderRepository.findAll().stream()
                .map(TemplateVariableDto::new)
                .collect(Collectors.toList());
    }
}
