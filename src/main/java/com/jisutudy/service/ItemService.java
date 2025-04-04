package com.jisutudy.service;

import com.jisutudy.domain.performance.Item;
import com.jisutudy.repository.JpaItemRepository;
import com.jisutudy.web.dto.ItemGetResponseDto;
import com.jisutudy.web.dto.ItemUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    public final JpaItemRepository jpaItemRepository;

    @Transactional
    public void saveItem(Item item) {
        jpaItemRepository.save(item);
    }

    public List<ItemGetResponseDto> findAll() {
        return jpaItemRepository.findAll().stream()
                .map(ItemGetResponseDto::new)
                .collect(Collectors.toList());
    }

    public ItemGetResponseDto findById(Long id) {
        Item item = jpaItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 없습니다."));
        return new ItemGetResponseDto(item);
    }

    @Transactional
    public void updateItem(ItemUpdateRequestDto requestDto) {
        Item findItem = jpaItemRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 없습니다."));
        findItem.update(requestDto.getName(), requestDto.getPrice(), requestDto.getStockQuantity());
    }
}
