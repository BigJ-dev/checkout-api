package com.host.checkout.data.enums;

public enum DiscountTypes {
    BULK_PURCHASE("bulkPurchase"), YOU("666");
    private String discountType;
    DiscountTypes(String discountType) {
        this.discountType = discountType;
    }
    public String getDiscountType() {
        return discountType;
    }
}
