package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Login.ClientType;
import com.JB.Project.Coupons.Login.LoginManager;
import com.JB.Project.Coupons.Services.AdminService;
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


        String StartDate = "2024/01/01";
        String EndDate = "2025/01/01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date Startdate = sdf.parse(StartDate);
        Date Enddate = sdf.parse(EndDate);
        long StartDateMilli = Startdate.getTime();
        long EndDatemilli = Enddate.getTime();
        java.sql.Date CouponStartDate = new java.sql.Date(StartDateMilli);
        java.sql.Date CouponEndDate = new java.sql.Date(EndDatemilli);

        Coupon coupon1 = Coupon.builder()
                .companyid(1)
                .categoryid(4)
                .title("Vacation")
                .description("15% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(7)
                .price(220.0f)
                .build();

        Coupon coupon2 = Coupon.builder()
                .companyid(1)
                .categoryid(1)
                .title("Food")
                .description("25% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(6)
                .price(150.0f)
                .build();

        Coupon coupon3 = Coupon.builder()
                .companyid(1)
                .categoryid(1)
                .title("Food")
                .description("30% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(2)
                .price(100.0f)
                .build();

        Coupon coupon4 = Coupon.builder()
                .companyid(2)
                .categoryid(1)
                .title("Food")
                .description("30% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(2)
                .price(100.0f)
                .build();

//        CompanyService userCompanyTest = LoginManager.getInstance().CompanyLogin("test@gmail.com", "12345678", ClientType.Company);
        CompanyService userCompanyTest = (CompanyService) LoginManager.getInstance("test@gmail.com", "12345678", ClientType.Company);

        userCompanyTest.addCoupon(coupon1);
        userCompanyTest.addCoupon(coupon2);
        userCompanyTest.addCoupon(coupon3);
        userCompanyTest.addCoupon(coupon4);
//        coupon1.setCompanyid(3);
//        coupon1.setId(10);
//        coupon1.setDescription("10% off");
//        userCompanyTest.updateCoupon(3, coupon1);
//    companyService.deleteCoupon(1);//
//        System.out.println(userCompanyTest.getAllCompanyCoupons(1));
//        System.out.println(userCompanyTest.getCompanyInfo(2));
//        System.out.println(userCompanyTest.getAllCategoryCoupons(1, 3));
//        System.out.println(userCompanyTest.getAllMaxPriceCoupons(1, 210.0f));

    }
}
