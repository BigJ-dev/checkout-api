package com.host.checkout.service;

import com.host.checkout.BaseTest;
import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.entity.PricingRule;
import com.host.checkout.data.enums.DiscountTypes;
import com.host.checkout.repository.PricingRuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.host.checkout.util.UtilFactory.Utils.roundOff;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DiscountServiceTest extends BaseTest {

    private DiscountService discountService;

    @Mock
    private PricingRuleRepository pricingRuleRepository;

    @BeforeEach
    void beforeEach() {
        discountService = new DiscountService(pricingRuleRepository);
    }

    @Test
    void applyDiscounts_BulkPurchaseDiscount() {
        //given
        when(pricingRuleRepository.findPricingRuleByCode("TSHIRT")).thenReturn(getDiscounts_BulkPurchaseDiscount());

        //when
        ItemDto itemDto = discountService.applyDiscounts(getItemDtoForTshirt());

        //then
        assertThat(itemDto).isNotNull();
        assertThat(roundOff(itemDto.getDiscount(), 1)).isEqualTo(roundOff(new BigDecimal(18.9), 1));
        assertThat(itemDto.getTotalPrice()).isEqualTo(new BigDecimal(63.00));
    }

    @Test
    void applyDiscounts_BuyXGetYFreeDiscount() {
        //given
        when(pricingRuleRepository.findPricingRuleByCode("MUG")).thenReturn(getPricingRuleForBUY_X_GET_Y_FREE());

        //when
        ItemDto itemDto = discountService.applyDiscounts(getItemDtoForMug());

        //then
        assertThat(itemDto).isNotNull();
        assertThat(itemDto.getDiscount()).isEqualTo(new BigDecimal(4.00));
        assertThat(itemDto.getTotalPrice()).isEqualTo(new BigDecimal(8.00));
    }

    protected ItemDto getItemDtoForMug() {
        return ItemDto.builder()
                .code("MUG")
                .quantity(2L)
                .build();
    }

    protected ItemDto getItemDtoForTshirt() {
        return ItemDto.builder()
                .code("TSHIRT")
                .quantity(3L)
                .build();
    }

    protected PricingRule getPricingRuleForBUY_X_GET_Y_FREE() {
        return PricingRule.builder()
                .code("MUG")
                .name("Triggerise Mug")
                .price(new BigDecimal(4.00))
                .discountType(DiscountTypes.BUY_X_GET_Y_FREE.getDiscountType())
                .minimumItems(2)
                .freeItemTotal(1)
                .discountPercentage(0.0)
                .build();
    }

    protected PricingRule getDiscounts_BulkPurchaseDiscount() {
        return PricingRule.builder()
                .code("TSHIRT")
                .name("Triggerise Tshirt")
                .price(new BigDecimal(21.00))
                .discountType(DiscountTypes.BULK_PURCHASE.getDiscountType())
                .minimumItems(3)
                .freeItemTotal(0)
                .discountPercentage(30.0)
                .build();
    }

}