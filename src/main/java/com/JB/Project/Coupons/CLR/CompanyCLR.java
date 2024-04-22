package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Order(3)
public class CompanyCLR implements CommandLineRunner {

    @Autowired
    CompanyService companyService;

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
                .categoryid(4)
                .title("Vacation")
                .description("15% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(5)
                .price(220.0f)
                .build();

        Coupon coupon2 = Coupon.builder()
                .companyid(1)
                .categoryid(4)
                .title("Vacatio")
                .description("25% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(5)
                .price(150.0f)
                .build();

        companyService.addCoupon(coupon1);
        companyService.addCoupon(coupon2);
        coupon1.setCompanyid(2);
        coupon1.setId(5);
        coupon1.setDescription("10% off");
        companyService.updateCoupon(2, coupon1);
//    companyService.deleteCoupon(2);
        //todo - fix delete coupon and make sure deletes purchases history
        System.out.println(companyService.getCompanyInfo(2));
        System.out.println(companyService.getAllCategoryCoupons(1,2));
        System.out.println(companyService.getAllMaxPriceCoupons(1,250.0f));

        //everything works without crashing :D
        // for now...

    }
}
