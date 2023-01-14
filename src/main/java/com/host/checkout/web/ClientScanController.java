package com.host.checkout.web;

import com.host.checkout.service.CheckoutService;
import com.host.checkout.util.UtilFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UtilFactory.Uri.GET_BASE_URI)
public class ClientScanController {

    private CheckoutService checkoutService;

    public ClientScanController(final CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/scan")
    public ResponseEntity itemScanner(@RequestBody List<String> items) {
        return ResponseEntity.ok(checkoutService.check(items));
    }
}
