package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Login.ClientType;
import com.JB.Project.Coupons.Login.LoginManager;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
import com.JB.Project.Coupons.Services.CompanyService;
import com.JB.Project.Coupons.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Order(4)
public class CustomerCLR implements CommandLineRunner {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CouponRepo couponRepo;

    @Autowired
    CustomerService customerService;


    @Override
    public void run(String... args) throws Exception {

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~temp~~~~~~~~~~~~~~~~~~~~
        String StartDate = "2024/01/01";
        String EndDate = "2025/01/01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date Startdate = sdf.parse(StartDate);
        Date Enddate = sdf.parse(EndDate);
        long StartDateMilli = Startdate.getTime();
        long EndDatemilli = Enddate.getTime();
        java.sql.Date CouponStartDate = new java.sql.Date(StartDateMilli);
        java.sql.Date CouponEndDate = new java.sql.Date(EndDatemilli);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~temp~~~~~~~~~~~~~~~~~~~~

        Coupon coupon1 = Coupon.builder()
                .companyid(1)
                .categoryid(2)
                .title("Electricity")
                .description("20% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(5)
                .price(200.0f)
                .build();

//        CustomerService userCustomerTest = LoginManager.getInstance().CustomerLogin("customer10@gmail.com", "12345678", ClientType.Customer);
        CustomerService userCustomerTest = (CustomerService) LoginManager.getInstance("customer10@gmail.com", "12345678", ClientType.Customer);

        userCustomerTest.purchaseCoupon(1, 1);
        userCustomerTest.purchaseCoupon(2, 1);
        System.out.println(userCustomerTest.getAllCustomerCoupons(1));
        System.out.println(userCustomerTest.getAllCustomerCoupons(2));
        System.out.println(userCustomerTest.getAllCategoryCoupons(1, 3));
        System.out.println(userCustomerTest.getAllCategoryCoupons(1, 4));
        System.out.println(userCustomerTest.getAllMaxPriceCoupons(1, 250));
        System.out.println(userCustomerTest.getCustomerInfo(2));
    }
}
