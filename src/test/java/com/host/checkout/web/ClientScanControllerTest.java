package com.host.checkout.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.host.checkout.BaseTest;
import com.host.checkout.data.dto.RequestDto;
import com.host.checkout.data.dto.ResponseDto;
import com.host.checkout.service.CheckoutService;
import com.host.checkout.service.PricingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.host.checkout.util.UtilFactory.Uri.GET_SCAN_ITEMS_URI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientScanController.class)
class ClientScanControllerTest extends BaseTest {

    @MockBean
    private CheckoutService checkoutService;

    @MockBean
    PricingService pricingService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void scanItems() throws Exception {
        //To-do: investigate the controller class injection.
        when(checkoutService.scan(getRequestDto())).thenReturn(getResponseDto());

        mockMvc.perform(get("/scan/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getRequestDto())));
        //.andDo(print()).andExpect(status().isOk())
    }

    protected RequestDto getRequestDto() {
        return RequestDto.builder().items(Arrays.asList("MUG", "TSHIRT", "MUG")).build();
    }

    protected ResponseDto getResponseDto() {
        return ResponseDto.builder().items(Arrays.asList("MUG", "TSHIRT", "MUG")).totalPrice("25.0€").build();
    }

    protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}