package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.dto.ResponseDto;
import com.host.checkout.repository.PricingRuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.host.checkout.data.mapper.Mapper.mapToResponseDto;

@Slf4j
@Service
public class PricingService {

    final PricingRuleRepository pricingRuleRepo;

    public PricingService(final PricingRuleRepository pricingRuleRepo) {
        this.pricingRuleRepo = pricingRuleRepo;
    }

    public ResponseDto getPricing(List<ItemDto> items) {
        return mapToResponseDto(getItemCodes(items), getItemsTotalPrice(items));
    }

    private BigDecimal getItemsTotalPrice(List<ItemDto> items) {
        return items.stream().filter(p -> pricingRuleRepo.existsByCode(p.getCode()))
                .map(p -> applyPricing(p)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<String> getItemCodes(List<ItemDto> items) {
        return items.stream().filter(p -> pricingRuleRepo.existsByCode(p.getCode()))
                .map(ItemDto::getCode).collect(Collectors.toList());
    }

    private BigDecimal applyPricing(ItemDto item) {
        BigDecimal price = pricingRuleRepo.findPricingRuleByCode(item.getCode()).getPrice();
        BigDecimal totalPrice = price.multiply(new BigDecimal(item.getCount()));

        return totalPrice;
    }
}
