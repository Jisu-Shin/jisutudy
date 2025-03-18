package com.jisutudy.service;

import com.jisutudy.domain.performance.Item;
import com.jisutudy.repository.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    public final JpaItemRepository jpaItemRepository;

    @Transactional
    public void saveItem(Item item) {
        jpaItemRepository.save(item);
    }

    public List<Item> findItems() {
        return jpaItemRepository.findAll();
    }

    public Item findById(Long id) {
        Item item = jpaItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 없습니다."));
        return item;
    }
}
