package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Services.AdminService;
import com.JB.Project.Coupons.Services.CompanyService;
import com.JB.Project.Coupons.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    @Autowired
    CompanyService companyService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    public abstract boolean Login(String Email,String Password);
}
