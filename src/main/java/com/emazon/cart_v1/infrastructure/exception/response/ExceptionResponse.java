package com.emazon.cart_v1.infrastructure.exception.response;

public enum ExceptionResponse {

    INVALID_TOKEN("Invalid or expired token"),

    ACCESS_DENIED("Access Denied. You do not have sufficient privileges to access this resource."),

    CART_NOT_FOUND("Cart Not Found"),;

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
