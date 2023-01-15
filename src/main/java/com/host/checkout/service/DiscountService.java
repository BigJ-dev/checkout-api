package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.entity.Discount;
import com.host.checkout.repository.DiscountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class DiscountService {
    final DiscountRepository discountRepo;

    public DiscountService(DiscountRepository discountRepo) {
        this.discountRepo = discountRepo;
    }

    public BigDecimal applyBulkPurchaseDiscount(ItemDto item) {
        Long quantity = item.getCount();
        BigDecimal unitPrice = item.getPrice();

        Discount discount = discountRepo.findDiscountByDiscountType(item.getDiscountType());
        if (quantity >= discount.getMinimumItems()) {
            return unitPrice.multiply(new BigDecimal((1 - discount.getDiscountPercentage()/100)));
        }
        return unitPrice;
    }
}
