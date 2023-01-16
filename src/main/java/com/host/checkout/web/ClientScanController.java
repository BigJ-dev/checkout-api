package com.host.checkout.web;

import com.host.checkout.data.dto.RequestDto;
import com.host.checkout.data.dto.ResponseDto;
import com.host.checkout.service.CheckoutService;
import com.host.checkout.util.UtilFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UtilFactory.Uri.GET_BASE_URI)
public class ClientScanController {

    private CheckoutService checkoutService;

    public ClientScanController(final CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping(value =UtilFactory.Uri.POST_SCAN_ITEMS_URI)
    public ResponseEntity<ResponseDto> itemScanner(@RequestBody RequestDto request) {
        return ResponseEntity.ok(checkoutService.scan(request));
    }
}
