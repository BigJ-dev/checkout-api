package com.host.checkout.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    private List<String> items;
}
