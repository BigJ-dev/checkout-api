package com.host.checkout.service;

import com.host.checkout.data.dto.ItemDto;
import com.host.checkout.data.dto.ResponseDto;
import com.host.checkout.data.entity.PricingRule;
import com.host.checkout.data.mapper.Mapper;
import com.host.checkout.exception.ResourceNotFound;
import com.host.checkout.repository.PricingRuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.host.checkout.data.mapper.Mapper.mapToResponseDto;

@Slf4j
@Service
public class PricingService {

    final PricingRuleRepository pricingRuleRepo;

    private DiscountService discountService;

    public PricingService(final PricingRuleRepository pricingRuleRepo, final DiscountService discountService) {
        this.pricingRuleRepo = pricingRuleRepo;
        this.discountService = discountService;
    }

    public ResponseDto getPricing(List<ItemDto> items) {
        List<ItemDto> itemDtoList = items.stream().filter(p -> pricingRuleRepo.existsByCode(p.getCode())).map(item -> applyPricing(item)).collect(Collectors.toList());
        ResponseDto response = mapToResponseDto(getItemCodes(itemDtoList), getItemsTotalPrice(itemDtoList));
        return response;
    }

    public List<PricingRule> getAllProducts() {
        return pricingRuleRepo.findAll();
    }

    public PricingRule updateItem(Long itemId, PricingRule pricingRule) {
        PricingRule existingPricingRule = pricingRuleRepo.findById(itemId).orElseThrow(() -> new ResourceNotFound("No item found, couldn't update"));
        Mapper.updateItem(existingPricingRule, pricingRule);
        return pricingRuleRepo.save(existingPricingRule);
    }

    public PricingRule deleteItem(Long itemId) {
        PricingRule pricingRule = pricingRuleRepo.findById(itemId).orElseThrow(() -> new ResourceNotFound("No item found, couldn't delete"));
        pricingRuleRepo.delete(pricingRule);
        return pricingRule;
    }

    public List<PricingRule> deleteAllItems() {
        List<PricingRule> itemsTobeRemoved = pricingRuleRepo.findAll().stream().collect(Collectors.toList());
        if (itemsTobeRemoved.isEmpty()) {
            throw new ResourceNotFound("No items exist, couldn't delete all");
        }
        pricingRuleRepo.deleteAll();
        return itemsTobeRemoved;
    }

    public PricingRule addItem(PricingRule item) {
        return pricingRuleRepo.save(item);
    }

    private ItemDto applyPricing(ItemDto dto) {
        ItemDto item = discountService.applyDiscounts(dto);
        return item;
    }

    private BigDecimal getItemsTotalPrice(List<ItemDto> items) {
        BigDecimal discounts = items.stream().map(ItemDto::getDiscount).collect(Collectors.reducing(BigDecimal.ZERO, BigDecimal::add));
        BigDecimal totalPrice = items.stream().map(ItemDto::getTotalPrice).collect(Collectors.reducing(BigDecimal.ZERO, BigDecimal::add));
        return totalPrice.subtract(discounts);
    }

    private List<String> getItemCodes(List<ItemDto> items) {
        return items.stream().map(p -> replicateItemCodes(p)).map(ItemDto::getReplicatedCodes).collect(Collectors.toList());
    }

    private ItemDto replicateItemCodes(ItemDto dto) {
        int convertedQuantity = Integer.valueOf(String.valueOf(dto.getQuantity()));

        List<String> codes = IntStream.range(0, convertedQuantity)
                .mapToObj(i -> dto.getCode())
                .collect(Collectors.toList());
        dto.setReplicatedCodes(code(codes));
        return dto;
    }

    private String code(List<String> codes) {
        return codes.toString().replace("[", "").replace("]", "");
    }
}
