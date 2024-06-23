package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
import com.JB.Project.Coupons.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CouponRepo couponRepo;

    @Override
    public boolean Login(String email, String password) {
        if (email.equals("customer10@gmail.com") && password.equals("12345678")) {
            return true;
        }
        return false;
    }

    @Override
    public void purchaseCoupon(int couponID, int customerID) throws CouponSystemException, ParseException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            Optional<Coupon> findCoupon = couponRepo.findById(couponID);
            if (findCoupon.isPresent()) {
                if (findCoupon.get().getAmount() > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    Date Enddate = sdf.parse(findCoupon.get().getEnd_date());
                    long EndDatemilli = Enddate.getTime();
                    java.sql.Date CouponEndDate = new java.sql.Date(EndDatemilli);
                    LocalDate couponEnd_Date = CouponEndDate.toLocalDate();
                    if (couponEnd_Date.isAfter(LocalDate.now()) || couponEnd_Date.isEqual(LocalDate.now())) {
                        Customer customer = findCustomer.get();
                        List<Coupon> customerCoupons = findCustomer.get().getCoupons();
                        customerCoupons.add(findCoupon.get());
                        customer.setCoupons(customerCoupons);
                        customerRepo.saveAndFlush(customer);
                        Coupon coupon = findCoupon.get();
                        coupon.setAmount(coupon.getAmount() - 1);
                        couponRepo.saveAndFlush(coupon);
                        System.out.println("Coupon purchased!");
                    } else {
                        System.out.println("Coupon is expired");
                    }
                } else {
                    System.out.println("Coupon is empty");
                }
            } else {
                System.out.println("Coupon does not exists");
            }
        } else {
            System.out.println("Customer does not exists");
        }
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            return findCustomer.get().getCoupons();
        } else {
            System.out.println("Customer does not exists");
            return null;
        }
    }

    @Override
    public List<Coupon> getAllCategoryCoupons(int customerID, int categoryID) throws CouponSystemException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            List<Coupon> CustomerCoupons = findCustomer.get().getCoupons();
            Predicate<Coupon> condition = coupon -> !coupon.getCategoryid().equals(categoryID);
            CustomerCoupons.removeIf(condition);
            return CustomerCoupons;
        } else {
            System.out.println("Customer does not exists");
            return null;
        }
    }

    @Override
    public List<Coupon> getAllMaxPriceCoupons(int customerID, float maxPrice) throws CouponSystemException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            List<Coupon> CustomerCoupons = findCustomer.get().getCoupons();
            Predicate<Coupon> condition = coupon -> coupon.getPrice() > maxPrice;
            CustomerCoupons.removeIf(condition);
            return CustomerCoupons;
        } else {
            System.out.println("Customer does not exists");
            return null;
        }
    }

    @Override
    public Customer getCustomerInfo(int customerID) throws CouponSystemException {
        return customerRepo.findById(customerID).orElseThrow(() -> new CouponSystemException(ErrorMessage.ID_NOT_FOUND));
    }
}
