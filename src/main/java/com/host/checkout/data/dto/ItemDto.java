package com.host.checkout.data.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemDto {
    private String code;
    private Long Count;
    private BigDecimal Price;
    private String discountType;
}
