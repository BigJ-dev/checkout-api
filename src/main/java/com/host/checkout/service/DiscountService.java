package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.entity.PricingRule;
import com.host.checkout.repository.PricingRuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class DiscountService {
    final PricingRuleRepository pricingRuleRepo;

    public DiscountService(PricingRuleRepository pricingRuleRepo) {
        this.pricingRuleRepo = pricingRuleRepo;
    }

    public BigDecimal applyBulkPurchaseDiscount(ItemDto item) {
        Long quantity = item.getCount();
        BigDecimal unitPrice = item.getPrice();
        PricingRule rule = pricingRuleRepo.findPricingRuleByCode(item.getCode());

        if (quantity >= rule.getMinimumItems()) {
            return unitPrice.multiply(new BigDecimal((1 - rule.getDiscountPercentage()/100)));
        }
        return unitPrice;
    }
}
