package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
//@Order(4)
public class CouponCLR implements CommandLineRunner {

    @Autowired
    CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {


        String StartDate = "2024/01/01";
        String EndDate = "2025/01/01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date Startdate = sdf.parse(StartDate);
        Date Enddate = sdf.parse(EndDate);
        long StartDateMilli = Startdate.getTime();
        long EndDatemilli = Enddate.getTime();
        java.sql.Date CouponStartDate = new java.sql.Date(StartDateMilli);
        java.sql.Date CouponEndDate = new java.sql.Date(EndDatemilli);

//        Coupon coupon1 = Coupon.builder()
//                .companyid(1)
//                .categoryid(4)
//                .title("Vacation")
//                .description("15% off")
//                .start_date(CouponStartDate)
//                .end_date(CouponEndDate)
//                .amount(5)
//                .price(250.0f)
//                .build();

//            couponRepo.save(coupon1);


    }
}
