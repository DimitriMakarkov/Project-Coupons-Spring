package com.JB.Project.Coupons.CLR.CustomerCLR;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Order(2)
public class AddCustomer implements CommandLineRunner{

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CouponRepo couponRepo;

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
                .company_id(1)
                .category_id(4)
                .title("Vacation")
                .description("15% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(5)
                .price(250.0f)
                .build();

        Customer customer1 = Customer.builder()
                .firstName("dima")
                .lastName("makarkov")
                .email("dima9650@gmail.com")
                .password("12345678")
                .coupon(coupon1)
                .build();

        customerRepo.save(customer1);
    }
}
