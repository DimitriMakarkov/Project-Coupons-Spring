package com.JB.Project.Coupons.Exceptions;

public class CouponSystemException extends Exception{

    public CouponSystemException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
