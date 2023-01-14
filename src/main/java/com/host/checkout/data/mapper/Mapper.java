package com.host.checkout.data.mapper;

import com.host.checkout.data.dto.ItemDto;

import java.util.Map;

public interface Mapper {
    static ItemDto map(Map.Entry<String, Long> item) {
        return ItemDto.builder()
                .name(item.getKey())
                .totalCount(item.getValue())
                .build();
    }
}
