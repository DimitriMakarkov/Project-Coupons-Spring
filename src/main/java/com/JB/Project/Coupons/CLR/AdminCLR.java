package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.*;
import com.JB.Project.Coupons.Login.ClientType;
import com.JB.Project.Coupons.Login.LoginManager;
import com.JB.Project.Coupons.Service_Implementation.UserServiceImp;
import com.JB.Project.Coupons.Services.AdminService;
import com.JB.Project.Coupons.Services.UserService;
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

    @Autowired
    UserService userService;

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
                .companyid(2)
                .categoryid(2)
                .title("Electricity")
                .description("20% off")
                .start_date("2024/01/01")
                .end_date("2025/01/01")
                .amount(5)
                .price(300.0f)
                .build();

        Coupon coupon2 = Coupon.builder()
                .companyid(1)
                .categoryid(3)
                .title("Restaurant")
                .description("10% off")
                .start_date("2024/01/01")
                .end_date("2025/01/01")
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

//        UserDetails userDetails = new UserDetails("dima9650@gmail.com","12345678", UserType.ADMIN);

//        AdminService userAdminTest = LoginManager.getInstance().AdminLogin("admin@admin.com", "admin", ClientType.Admin);
        AdminService userAdminTest = (AdminService) LoginManager.getInstance("admin@admin.com", "admin", ClientType.Admin);

//        userAdminTest.addCompany(company1);
//        userAdminTest.addCompany(company2);
//        userAdminTest.addCompany(company3);

//        userService.registerUser(userDetails);

        Customer customer1 = Customer.builder()
                .firstName("Testcustomer1")
                .lastName("one")
                .email("customer10@gmail.com")
                .password("12345678")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Testcustomer2")
                .lastName("two")
                .email("customer2@gmail.com")
                .password("87654321")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("Testcustomer3")
                .lastName("three")
                .email("customer3@gmail.com")
                .password("12345678")
                .build();

//        userAdminTest.addCustomer(customer1);
//        userAdminTest.addCustomer(customer2);
//        userAdminTest.addCustomer(customer3);



    }
}