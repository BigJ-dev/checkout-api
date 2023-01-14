package com.host.checkout.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
public class DiscountService {
    public BigDecimal applyDiscounts(Map<String, Integer> itemCounts) {
        BigDecimal total = new BigDecimal(0);
        // Apply 2-for-1 discount on MUG items
        int mugCount = itemCounts.getOrDefault("MUG", 0);
        int freeMugCount = mugCount / 2;
        int mugCountToBePaid = mugCount - freeMugCount;
        total = total.add(new BigDecimal(mugCountToBePaid * 4));
        return total;
    }
}
