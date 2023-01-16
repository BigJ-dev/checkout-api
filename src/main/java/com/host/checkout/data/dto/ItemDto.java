package com.host.checkout.data.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemDto {
    private String code;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String discountType;
    private BigDecimal discount;
}
