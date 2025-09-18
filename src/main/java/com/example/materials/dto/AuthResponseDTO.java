package com.example.materials.controller;

public class AuthResponseDTO {
    private String tokenOrMessage;
    public AuthResponseDTO(String tokenOrMessage) { this.tokenOrMessage = tokenOrMessage; }
    public String getTokenOrMessage() { return tokenOrMessage; }
    public void setTokenOrMessage(String tokenOrMessage) { this.tokenOrMessage = tokenOrMessage; }
}
