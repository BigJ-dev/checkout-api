package com.host.checkout.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PricingService {

    private Map<String, Integer> itemCounts;

    public void PricingRule() {
        this.itemCounts = new HashMap<>();
    }

    public void scan(String itemCode) {
        if (itemCounts.containsKey(itemCode)) {
            itemCounts.put(itemCode, itemCounts.get(itemCode) + 1);
        } else {
            itemCounts.put(itemCode, 1);
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);


        int mugCount = itemCounts.getOrDefault("MUG", 0);
        int freeMugCount = mugCount / 2;
        int mugCountToBePaid = mugCount - freeMugCount;
        total = total.add(new BigDecimal(mugCountToBePaid * 4));

        // Apply bulk discount on TSHIRT items
        int tshirtCount = itemCounts.getOrDefault("TSHIRT", 0);
        if (tshirtCount >= 3) {
            total = total.add(new BigDecimal(tshirtCount * 21 * 0.7));
        } else {
            total = total.add(new BigDecimal(tshirtCount * 21));
        }

        // Add USBKEY items
        int usbkeyCount = itemCounts.getOrDefault("USBKEY", 0);
        total = total.add(new BigDecimal(usbkeyCount * 10));

        return total;
    }
}
