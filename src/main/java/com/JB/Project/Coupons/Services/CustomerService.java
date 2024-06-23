package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {

    boolean Login(String email,String password);

    void purchaseCoupon(int couponID,int customerID) throws CouponSystemException, ParseException;

    List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException;

    List<Coupon> getAllCategoryCoupons(int customerID,int categoryID) throws CouponSystemException;

    List<Coupon> getAllMaxPriceCoupons(int customerID,float maxPrice) throws CouponSystemException;

    Customer getCustomerInfo(int customerID) throws CouponSystemException;
}
