package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
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
//            throw new CouponSystemException(ErrorMessage.COUPON_TITLE_EXISTS);
        }
    }

    @Override
    public void updateCoupon(int couponID, Coupon coupon) throws CouponSystemException {
        Optional<Coupon> findCompanyCoupon = couponRepo.findById(couponID);
        if (findCompanyCoupon.isPresent()) {
            Coupon updatedCoupon = findCompanyCoupon.get();
            updatedCoupon.setAmount(coupon.getAmount());
            updatedCoupon.setDescription(coupon.getDescription());
            updatedCoupon.setCategoryid(coupon.getCategoryid());
            updatedCoupon.setStart_date(coupon.getStart_date());
            updatedCoupon.setEnd_date(coupon.getEnd_date());
            updatedCoupon.setTitle(coupon.getTitle());
            updatedCoupon.setPrice(coupon.getPrice());
            updatedCoupon.setImage(coupon.getImage());
            couponRepo.saveAndFlush(updatedCoupon);
            System.out.println("Coupon has been updated");
        } else {
            System.out.println("Coupon not found...");
//            throw new CouponSystemException(ErrorMessage.COUPON_NOT_FOUND);
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
//            throw new CouponSystemException(ErrorMessage.COUPON_NOT_FOUND);
        }
    }

    @Override
    public List<Coupon> getAllCompanyCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public List<Coupon> getAllCategoryCoupons(int company_id,int category_id) throws CouponSystemException {
       List<Coupon> CompanyCoupons = couponRepo.findAllByCompanyid(company_id);
        Predicate<Coupon> condition = coupon -> coupon.getCategoryid().equals(category_id);
        CompanyCoupons.removeIf(condition);//todo - check if it deletes the coupon by accident
        return CompanyCoupons;
    }


    @Override
    public List<Coupon> getAllMaxPriceCoupons(int company_id,float maxPrice) throws CouponSystemException {
        List<Coupon> CompanyCoupons = couponRepo.findAllByCompanyid(company_id);
        Predicate<Coupon> condition = coupon -> coupon.getPrice()>maxPrice;
        CompanyCoupons.removeIf(condition);
        return CompanyCoupons;
    }

    @Override
    public Company getCompanyInfo(int companyID) throws CouponSystemException{
        return companyRepo.findById(companyID).orElseThrow(() -> new CouponSystemException(ErrorMessage.ID_NOT_FOUND));
    }
}
