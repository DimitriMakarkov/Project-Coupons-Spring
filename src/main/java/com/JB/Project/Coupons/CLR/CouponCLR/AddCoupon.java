package com.JB.Project.Coupons.CLR.CouponCLR;

import com.JB.Project.Coupons.Repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class AddCoupon implements CommandLineRunner {

    @Autowired
    CouponRepo couponRepo;
    @Override
    public void run(String... args) throws Exception {





    }
}
