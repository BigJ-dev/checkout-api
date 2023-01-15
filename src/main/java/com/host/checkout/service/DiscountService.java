package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.entity.PricingRule;
import com.host.checkout.data.enums.DiscountTypes;
import com.host.checkout.repository.PricingRuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.host.checkout.data.mapper.Mapper.mapToItem;

@Slf4j
@Service
public class DiscountService {
    final PricingRuleRepository pricingRuleRepo;

    public DiscountService(PricingRuleRepository pricingRuleRepo) {
        this.pricingRuleRepo = pricingRuleRepo;
    }
    public BigDecimal applyDiscount(ItemDto dto){
        PricingRule rule = pricingRuleRepo.findPricingRuleByCode(dto.getCode());
        ItemDto item = mapToItem(rule, dto);

        if(DiscountTypes.BULK_PURCHASE.getDiscountType().equalsIgnoreCase(rule.getDiscountType())){
            applyBulkPurchaseDiscount(item, rule);
        }
        return item.getTotalPrice();
    }

    private BigDecimal applyBulkPurchaseDiscount(final ItemDto item, final PricingRule rule) {
        BigDecimal discountPercentage = BigDecimal.valueOf(rule.getDiscountPercentage());
        if (item.getQuantity() >= rule.getMinimumItems()) {
            item.setTotalPrice(item.getTotalPrice().multiply(discountPercentage).divide(new BigDecimal(100)));
        }
        return item.getTotalPrice();
    }
}
