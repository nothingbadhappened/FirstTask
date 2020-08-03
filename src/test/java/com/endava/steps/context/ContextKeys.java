package com.endava.steps.context;

public enum ContextKeys {
    CURRENT_PAGE ("CURRENT PAGE"),
    HOME_PAGE ("PAGE"),
    LOGIN_PAGE ("PAGE"),
    MY_ACCOUNT_PAGE ("PAGE"),
    PRODUCT_PAGE ("PAGE"),
    SEARCH_PAGE ("PAGE"),
    CART_PAGE ("PAGE"),
    CURRENT_USER ("CURRENT USER"),
    USER_REGISTERED ("USER"),
    USER_NOT_REGISTERED ("USER");

    private String type;

    ContextKeys(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
