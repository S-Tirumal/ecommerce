package com.ecom.productcatalogue.config;

public class TokenHolder {
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static void setToken(String jwt) {
        token.set(jwt);
    }

    public static String getToken() {
        return token.get();
    }

    public static void clear() {
        token.remove();
    }
}