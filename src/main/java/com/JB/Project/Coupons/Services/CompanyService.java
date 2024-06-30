package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import java.util.List;

public interface CompanyService {

    boolean Login(String email,String password);

    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponID,Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponID) throws CouponSystemException;

    List<Coupon> getAllCompanyCoupons(int companyID) throws CouponSystemException;

    Coupon getSingleCoupon(int couponID) throws CouponSystemException;

    List<Coupon> getAllCategoryCoupons(int companyID,int categoryID) throws CouponSystemException;

    List<Coupon> getAllMaxPriceCoupons(int companyID,float MaxPrice) throws CouponSystemException;

    Company getCompanyInfo(String email,String password) throws CouponSystemException;
}
