package com.test.test.controller.dto;

public class CreateWalletDto {

    private UserDto user;
    private String currencyName;

    public UserDto getUser() {
        return user;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

}
