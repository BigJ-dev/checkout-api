package com.host.checkout.web;

import com.host.checkout.data.dto.RequestDto;
import com.host.checkout.data.dto.ResponseDto;
import com.host.checkout.data.entity.PricingRule;
import com.host.checkout.service.CheckoutService;
import com.host.checkout.service.PricingService;
import com.host.checkout.util.UtilFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UtilFactory.Uri.GET_BASE_URI)
public class ClientScanController {

    private CheckoutService checkoutService;
    private PricingService pricingService;

    public ClientScanController(final CheckoutService checkoutService, PricingService pricingService) {
        this.checkoutService = checkoutService;
        this.pricingService = pricingService;
    }

    @GetMapping(value =UtilFactory.Uri.GET_SCAN_ITEMS_URI)
    public ResponseEntity<ResponseDto> itemScanner(@RequestBody RequestDto request) {
        return ResponseEntity.ok(checkoutService.scan(request));
    }

    @GetMapping(value =UtilFactory.Uri.GET_ALL_PRODUCTS_URI)
    public ResponseEntity<List<PricingRule>> getAllProducts() {
        return ResponseEntity.ok(pricingService.getAllProducts());
    }
}
