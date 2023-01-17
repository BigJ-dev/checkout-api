package com.host.checkout.util;

public interface UtilFactory {
    class Uri {
        public final static String GET_BASE_URI = "/api/checkout-application";
        public final static String GET_SCAN_ITEMS_URI = "/scan";
        public final static String GET_ALL_PRODUCTS_URI = "/get-products";
        public final static String POST_ITEM_RULE_URI = "/add-item";
        public final static String PUT_ITEM_RULE_URI = "/update-item/{id}";
        public final static String DELETE_ITEM_RULE_URI = "/delete-item/{id}";
        public final static String DELETE_ALL_ITEM_RULE_URI = "/delete-all-items";
    }
}
