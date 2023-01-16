package com.host.checkout.data.mapper;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.dto.ResponseDto;
import com.host.checkout.data.entity.PricingRule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Mapper {
    static ItemDto mapToItem(Map.Entry<String, Long> item) {
        return ItemDto.builder()
                .code(item.getKey())
                .quantity(item.getValue())
                .build();
    }

    static ResponseDto mapToResponseDto(List<String> codes, BigDecimal totalPrice) {
        return ResponseDto.builder()
                .items(codes)
                .totalPrice(totalPrice.toString().concat("€"))
                .build();
    }

    static PricingRule updateItem(PricingRule item) {
        return PricingRule.builder()
                .code(item.getCode())
                .name(item.getName())
                .price(item.getPrice())
                .discountType(item.getDiscountType())
                .minimumItems(item.getMinimumItems())
                .discountPercentage(item.getDiscountPercentage())
                .build();
    }

    static ItemDto mapToItem(PricingRule pricingRule, ItemDto item) {
        item.setUnitPrice(pricingRule.getPrice());
        item.setDiscountType(pricingRule.getDiscountType());
        item.setTotalPrice(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));

        return item;
    }
}
