package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponID,Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponID) throws CouponSystemException;

    List<Coupon> getAllCompanyCoupons(int companyID) throws CouponSystemException;

    List<Coupon> getAllCategoryCoupons(int companyID,int categoryID) throws CouponSystemException;

    List<Coupon> getAllMaxPriceCoupons(int companyID,float MaxPrice) throws CouponSystemException;

    Company getCompanyInfo(int comapanyID) throws CouponSystemException;
}
