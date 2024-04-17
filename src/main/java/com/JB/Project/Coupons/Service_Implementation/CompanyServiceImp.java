package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    CouponRepo couponRepo;

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        Optional<Coupon> findCompanyCoupon = couponRepo.findByTitle(coupon.getTitle());
        if (!findCompanyCoupon.isPresent()) {
            couponRepo.save(coupon);
            System.out.println("Coupon saved successfully!");
        } else {
            System.out.println("Coupon with the same title exists");
            throw new CouponSystemException(ErrorMessage.COUPON_TITLE_EXISTS);
        }
    }

    @Override
    public void updateCoupon(int couponID, Coupon coupon) throws CouponSystemException {
        Optional<Coupon> findCompanyCoupon = couponRepo.findById(couponID);
        if (findCompanyCoupon.isPresent()) {
            couponRepo.saveAndFlush(coupon);
            System.out.println("Coupon has been updated");
        } else {
            System.out.println("Coupon not found...");
            throw new CouponSystemException(ErrorMessage.COUPON_NOT_FOUND);
        }
    }

    @Override
    public void deleteCoupon(int couponID) throws CouponSystemException {
        Optional<Coupon> findComapnyCoupon = couponRepo.findById(couponID);
        if (findComapnyCoupon.isPresent()) {
            couponRepo.deleteById(couponID);
            System.out.println("Coupon has been deleted");
        } else {
            System.out.println("Coupon not found...");
            throw new CouponSystemException(ErrorMessage.COUPON_NOT_FOUND);
        }
    }

    @Override
    public List<Coupon> getAllCompanyCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public List<Coupon> getAllCategoryCoupons(int categoryID) throws CouponSystemException {
        return couponRepo.findAllByCategory(categoryID);
    }

    @Override
    public List<Coupon> getAllMaxPriceCoupons(int maxPrice) throws CouponSystemException {
        return couponRepo.findAllByMaxPrice(maxPrice);
    }

    @Override
    public Company getCompanyInfo() {
        return null;
    }
}
