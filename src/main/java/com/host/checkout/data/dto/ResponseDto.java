package com.host.checkout.data.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResponseDto {
    private List<String> items;
    private BigDecimal totalPrice;
}
