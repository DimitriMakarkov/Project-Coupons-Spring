package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
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

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public boolean Login(String email, String password) {
        if (email.equals("test@gmail.com") && password.equals("12345678")) {
            return true;
        }
        return false;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        Optional<Coupon> findCompanyCoupon = couponRepo.findByTitle(coupon.getTitle());
        if (!findCompanyCoupon.isPresent()) {
            System.out.println(coupon);
            couponRepo.save(coupon);
            System.out.println("Coupon saved successfully!");
        } else {
            if (coupon.getCompanyid().equals(findCompanyCoupon.get().getCompanyid())) {
                System.out.println("Coupon with the same title exists...");
//            throw new CouponSystemException(ErrorMessage.COUPON_TITLE_EXISTS);
            } else {
                System.out.println(coupon);
                couponRepo.save(coupon);
                System.out.println("Coupon saved successfully!");
            }
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
            System.out.println(updatedCoupon);
            couponRepo.saveAndFlush(updatedCoupon);
            System.out.println("Coupon has been updated");
        } else {
            System.out.println("Coupon not found...");
//            throw new CouponSystemException(ErrorMessage.COUPON_NOT_FOUND);
        }
    }

    @Override
    public void deleteCoupon(int couponID) throws CouponSystemException {
        Optional<Coupon> findCustomerCoupon = couponRepo.findById(couponID);
        if (findCustomerCoupon.isPresent()) {
            List<Customer> CustomersWithCoupons = customerRepo.findByCouponsId(couponID);
            System.out.println(CustomersWithCoupons);
            if (!CustomersWithCoupons.isEmpty()) {
                for (Customer customer : CustomersWithCoupons) {
//                    customer.setCoupons(null);
                }
                customerRepo.saveAllAndFlush(CustomersWithCoupons);
                couponRepo.deleteById(couponID);
                System.out.println("Coupon has been deleted");
            } else {
                System.out.println("No customers with coupons");
            }
        } else {
            System.out.println("Coupon not found...");
        }
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyID) throws CouponSystemException {
        List<Coupon> companyCoupons = couponRepo.findAllByCompanyid(companyID);
        return companyCoupons;
    }

    @Override
    public Coupon getSingleCoupon(int couponID) throws CouponSystemException {
        return couponRepo.findById(couponID).orElseThrow(() -> new CouponSystemException(ErrorMessage.COUPON_NOT_FOUND));
    }

    @Override
    public List<Coupon> getAllCategoryCoupons(int company_id, int category_id) throws CouponSystemException {
        List<Coupon> CompanyCoupons = couponRepo.findAllByCompanyid(company_id);
        Predicate<Coupon> condition = coupon -> !coupon.getCategoryid().equals(category_id);
        CompanyCoupons.removeIf(condition);
        return CompanyCoupons;
    }


    @Override
    public List<Coupon> getAllMaxPriceCoupons(int company_id, float maxPrice) throws CouponSystemException {
        List<Coupon> CompanyCoupons = couponRepo.findAllByCompanyid(company_id);
        Predicate<Coupon> condition = coupon -> coupon.getPrice() > maxPrice;
        CompanyCoupons.removeIf(condition);
        return CompanyCoupons;
    }

    @Override
    public Company getCompanyInfo(String email,String password) throws CouponSystemException {
        return companyRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new CouponSystemException(ErrorMessage.ID_NOT_FOUND));
    }
}
