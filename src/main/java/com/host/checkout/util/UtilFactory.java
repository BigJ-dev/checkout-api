package com.host.checkout.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

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

    class Utils {
        public static String convertArrayListToString(List<String> list) {
            return list.toString().replace("[", "").replace("]", "");
        }

        public static BigDecimal roundOff(BigDecimal value, int round){
            BigDecimal roundedNumber = value.setScale(round, RoundingMode.HALF_UP);
            return roundedNumber;
        }
    }
}
