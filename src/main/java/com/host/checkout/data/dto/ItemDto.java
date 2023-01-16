package com.host.checkout.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String code;
    private String replicatedCodes;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String discountType;
    private BigDecimal discount;
}
