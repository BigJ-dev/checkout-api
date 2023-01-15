package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.dto.RequestDto;
import com.host.checkout.data.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.host.checkout.data.mapper.Mapper.mapToItem;

@Slf4j
@Service
public class CheckoutService {
    private PricingService pricingService;

    public CheckoutService(final PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public ResponseDto scan(RequestDto request) {
        List<ItemDto> items = getDistinctItemCounts(request);
        return pricingService.getPricing(items);
    }

    private List<ItemDto> getDistinctItemCounts(RequestDto request) {
        Map<String, Long> distinctItems  = request.getItems().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return distinctItems.entrySet().stream().map(entry -> mapToItem(entry)).collect(Collectors.toList());
    }
}
