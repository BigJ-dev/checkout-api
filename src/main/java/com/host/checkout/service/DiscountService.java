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

    public ItemDto applyDiscounts(ItemDto dto) {
        PricingRule pricingRule = pricingRuleRepo.findPricingRuleByCode(dto.getCode());
        ItemDto item = calculateItemDiscount(dto, pricingRule);
        return item;
    }

    private ItemDto calculateItemDiscount(final ItemDto itemDto, final PricingRule pricingRule) {
        ItemDto item = mapToItem(pricingRule, itemDto);
        if (DiscountTypes.BULK_PURCHASE.getDiscountType().equalsIgnoreCase(item.getDiscountType())) {
            applyBulkPurchaseDiscount(item, pricingRule);
        } else if (DiscountTypes.BUY_X_GET_Y_FREE.getDiscountType().equalsIgnoreCase(item.getDiscountType())) {
            applyBxGyFreeDiscount(item, pricingRule);
        } else if (DiscountTypes.NONE.getDiscountType().equalsIgnoreCase(item.getDiscountType())) {
            applyNoneDiscount(item);
        }
        return item;
    }

    private ItemDto applyBulkPurchaseDiscount(final ItemDto item, final PricingRule rule) {
        BigDecimal discountPercentage = BigDecimal.valueOf(rule.getDiscountPercentage());
        if (item.getQuantity() >= rule.getMinimumItems()) {
            BigDecimal discount = new BigDecimal(item.getQuantity()).multiply(item.getUnitPrice()).multiply(discountPercentage).divide(new BigDecimal(100));
            item.setDiscount(discount);
        }
        return item;
    }

    private ItemDto applyBxGyFreeDiscount(final ItemDto item, final PricingRule pricingRule) {
        if (item.getQuantity() >= pricingRule.getMinimumItems()) {
            int freeItemQuantity = Math.toIntExact(item.getQuantity() / 2);
            BigDecimal discount = new BigDecimal(freeItemQuantity).multiply(item.getUnitPrice());
            item.setDiscount(discount);
        }
        return item;
    }

    private ItemDto applyNoneDiscount(final ItemDto item) {
        item.setDiscount(new BigDecimal(0.0));
        return item;
    }
}
