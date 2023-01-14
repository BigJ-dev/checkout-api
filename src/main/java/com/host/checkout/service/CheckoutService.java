package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.dto.RequestDto;
import com.host.checkout.data.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.host.checkout.data.mapper.Mapper.map;

@Slf4j
@Service
public class CheckoutService {
    private PricingService pricingService;

    public CheckoutService(final PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public ResponseDto scan(RequestDto request) {


        getDistinctItemCounts(request).entrySet().stream().forEach(entry ->{
            ItemDto item = map(entry);

            Map<String, Integer> itemTotal = pricingService.getItemTotal(item);
        });

        return null;
    }

    private Map<String, Long> getDistinctItemCounts(RequestDto request) {
        return request.getItems().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
