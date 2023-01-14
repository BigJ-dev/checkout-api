package com.host.checkout.data.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemDto {
    private String name;
    private Long totalCount;
    private BigDecimal totalPrice;
}
