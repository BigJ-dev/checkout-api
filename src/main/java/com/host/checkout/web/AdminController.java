package com.host.checkout.web;

import com.host.checkout.data.entity.PricingRule;
import com.host.checkout.service.PricingService;
import com.host.checkout.util.UtilFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-checkout")
public class AdminController {
    final PricingService pricingService;

    public AdminController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping(value = UtilFactory.Uri.POST_ITEM_RULE_URI)
    public ResponseEntity<PricingRule> creatItem(@RequestBody PricingRule pricingRule) {
        return ResponseEntity.ok().body(pricingService.addItem(pricingRule));
    }

    @PutMapping(value = UtilFactory.Uri.PUT_ITEM_RULE_URI)
    public ResponseEntity<PricingRule> updateItem(@PathVariable("id") Long itemId, @RequestBody PricingRule pricingRule) {
        return ResponseEntity.ok().body(pricingService.updateItem(itemId, pricingRule));
    }

    @DeleteMapping(value = UtilFactory.Uri.DELETE_ITEM_RULE_URI)
    public ResponseEntity<PricingRule> deleteItem(@PathVariable("id") Long itemId) {
        return ResponseEntity.ok().body(pricingService.deleteItem(itemId));
    }

    @DeleteMapping(value = UtilFactory.Uri.DELETE_ALL_ITEM_RULE_URI)
    public ResponseEntity<List<PricingRule>> deleteAllItems() {
        return ResponseEntity.ok().body(pricingService.deleteAllItems());
    }

}
