package com.host.checkout.data.mapper;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Mapper {
    static ItemDto map(Map.Entry<String, Long> item) {
        return ItemDto.builder()
                .name(item.getKey())
                .Count(item.getValue())
                .build();
    }

    static ResponseDto mapToResponseDto(List<String> names, BigDecimal totalPrice) {
        return ResponseDto.builder()
                .items(names)
                .totalPrice(totalPrice)
                .build();
    }
}
