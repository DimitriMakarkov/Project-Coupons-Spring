package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Order(2)
public class AdminCLR implements CommandLineRunner {

    @Autowired
    AdminService adminService;

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
                .companyid(2)
                .categoryid(2)
                .title("Electricity")
                .description("20% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(5)
                .price(300.0f)
                .build();

        Coupon coupon2 = Coupon.builder()
                .companyid(1)
                .categoryid(3)
                .title("Restaurant")
                .description("10% off")
                .start_date(CouponStartDate)
                .end_date(CouponEndDate)
                .amount(3)
                .price(200.0f)
                .build();

        Company company1 = Company.builder()
                .name("test")
                .email("test@gmail.com")
                .password("12345678")
                .coupon(coupon2)
                .build();

        Company company2 = Company.builder()
                .name("something")
                .email("something@gmail.com")
                .password("87654321")
                .coupon(coupon1)
                .build();

        Company company3 = Company.builder()
                .name("nothing")
                .email("nothing@gmail.com")
                .password("12345678")
                .build();


//todo - add sout to each service for showing in console what it saved/updated/deleted/ect...
        adminService.addCompany(company1);
        adminService.addCompany(company1);
        adminService.addCompany(company2);
        adminService.addCompany(company3);
        company3.setPassword("87654321");
        adminService.updateCompany(3,company3);
            company3.setName("new name");
        adminService.updateCompany(3,company3);
////            adminService.deleteCompany(3);
        System.out.println(adminService.getAllCompanies());
        System.out.println(adminService.getSingleCompany(1));

        Customer customer1 = Customer.builder()
                .firstName("dima")
                .lastName("makarkov")
                .email("dima9650@gmail.com")
                .password("12345678")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("kosta")
                .lastName("makarkov")
                .email("kosta@gmail.com")
                .password("87654321")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("valeri")
                .lastName("makarkov")
                .email("valeri@gmail.com")
                .password("12345678")
                .build();

        adminService.addCustomer(customer1);
        adminService.addCustomer(customer2);
        adminService.addCustomer(customer2);
        adminService.addCustomer(customer3);
        customer3.setPassword("87654321");
        adminService.updateCustomer(3,customer3);
        customer3.setId(10);
        adminService.updateCustomer(3,customer3);
        adminService.deleteCustomer(3);
        System.out.println(adminService.getAllCustomers());
        System.out.println(adminService.getSingleCustomer(2));


    }
}