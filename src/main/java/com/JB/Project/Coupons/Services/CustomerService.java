package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {

    void purchaseCoupon(int couponID) throws CouponSystemException;

    List<Coupon> getAllCustomerCoupons();

    List<Coupon> getAllCategoryCoupons(int categoryID) throws CouponSystemException;

    List<Coupon> getAllMaxPriceCoupons(int maxPrice) throws CouponSystemException;

    Customer getCustomerInfo();
}
