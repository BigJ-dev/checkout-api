package com.host.checkout.data.enums;

public enum DiscountTypes {
    BULK_PURCHASE("bulkPurchase"), BUY_X_GET_Y_FREE("BOGO");
    private String discountType;
    DiscountTypes(String discountType) {
        this.discountType = discountType;
    }
    public String getDiscountType() {
        return discountType;
    }
}
