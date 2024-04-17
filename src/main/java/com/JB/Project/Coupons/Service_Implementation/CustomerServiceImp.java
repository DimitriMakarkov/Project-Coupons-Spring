package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Services.CustomerService;

import java.util.List;

public class CustomerServiceImp implements CustomerService {
    @Override
    public void purchaseCoupon(int couponID) throws CouponSystemException {

    }

    @Override
    public List<Coupon> getAllCustomerCoupons() {
        return null;
    }

    @Override
    public List<Coupon> getAllCategoryCoupons(int categoryID) throws CouponSystemException {
        return null;
    }

    @Override
    public List<Coupon> getAllMaxPriceCoupons(int maxPrice) throws CouponSystemException {
        return null;
    }

    @Override
    public Customer getCustomerInfo() {
        return null;
    }
}
