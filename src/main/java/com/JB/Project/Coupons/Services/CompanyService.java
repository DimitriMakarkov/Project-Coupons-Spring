package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponID,Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponID) throws CouponSystemException;

    List<Coupon> getAllCompanyCoupons();

    List<Coupon> getAllCategoryCoupons(int categoryID) throws CouponSystemException;

    List<Coupon> getAllMaxPriceCoupons(int MaxPrice) throws CouponSystemException;

    Company getCompanyInfo();
}
