package com.travelbees.entity;

public class AuthenticationResponse {
    private String token;
    private String message;
    
    								
    public AuthenticationResponse(String token,String message) {
        this.token = token;
        this.message = message;
    }
    public AuthenticationResponse(String message) {
       
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
