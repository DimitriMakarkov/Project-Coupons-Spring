package com.JB.Project.Coupons.Exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    ID_NOT_FOUND("ID not found"),
    ID_ALREADY_EXISTS("ID already exists"),

    COMPANY_NOT_FOUND("Company not found"),
    COMPANY_NAME_EXISTS("Company with the same name already exists"),
    COMPANY_EMAIL_EXISTS("Company with the same email already exists"),
    COMPANY_CANNOT_CHANGE_NAME("Cannot change company name"),

    CUSTOMER_EMAIL_EXISTS("Customer with the same email already exists"),
    CUSTOMER_NOT_FOUND("Customer not found"),

    COUPON_TITLE_EXISTS("Coupon already exists"),
    COUPON_NOT_FOUND("Coupon not found");

    //todo : add more exceptions

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
