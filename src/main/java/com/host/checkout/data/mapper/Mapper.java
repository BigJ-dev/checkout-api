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

    static PricingRule updateItem(PricingRule existingPricingRule,PricingRule item) {
        existingPricingRule.setId(item.getId());
        existingPricingRule.setCode(item.getCode());
        existingPricingRule.setPrice(item.getPrice());
        existingPricingRule.setDiscountType(item.getDiscountType());
        existingPricingRule.setMinimumItems(item.getMinimumItems());
        existingPricingRule.setDiscountType(item.getDiscountType());
        existingPricingRule.setFreeItemTotal(item.getFreeItemTotal());
        return existingPricingRule;
    }

    static ItemDto mapToItem(PricingRule pricingRule, ItemDto item) {
        item.setDiscount(new BigDecimal(0.0));
        item.setUnitPrice(pricingRule.getPrice());
        item.setDiscountType(pricingRule.getDiscountType());
        item.setTotalPrice(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));

        return item;
    }
}
