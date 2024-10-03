package com.abhi_ecommerce.response;

public class AuthResponse {

    private String jwt;
    private String message;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public AuthResponse() {
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
