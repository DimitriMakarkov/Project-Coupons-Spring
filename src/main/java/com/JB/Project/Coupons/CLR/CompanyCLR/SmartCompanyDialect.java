package com.JB.Project.Coupons.CLR.CompanyCLR;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SmartCompanyDialect implements CommandLineRunner {

    @Autowired
    CompanyRepo companyRepo;
    @Override
    public void run(String... args) throws Exception {

    }
}
