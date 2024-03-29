package com.JB.Project.Coupons.Exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    ID_NOT_FOUND("ID not found..."),
    ID_ALREADY_EXISTS("ID already exists..."),
    COMPANY_NOT_FOUND("Company not found...");

    //todo : add more exceptions

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
