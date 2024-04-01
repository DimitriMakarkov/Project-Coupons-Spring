package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Component
@Order(1)
public class CompanyCLR implements CommandLineRunner {

    @Autowired
    CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {

        Company company1 = Company.builder()
                .name("test")
                .email("test@gmail.com")
                .password("12345678")
                .build();

        companyRepo.save(company1);
    }
}
