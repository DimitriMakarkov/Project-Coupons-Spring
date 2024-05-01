package com.JB.Project.Coupons.Login;

import com.JB.Project.Coupons.Services.AdminService;
import com.JB.Project.Coupons.Services.CompanyService;
import com.JB.Project.Coupons.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {

    private static AdminService adminService;

    private static CompanyService companyService;

    private static CustomerService customerService;

    @Autowired
    private LoginManager(AdminService adminService, CompanyService companyService, CustomerService customerService) {
        this.adminService = adminService;
        this.companyService = companyService;
        this.customerService = customerService;
    }

    private static LoginManager instance;

    public static Object getInstance(String email, String password, ClientType Type) {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    switch (Type) {
                        case Admin:
                            if (adminService.Login(email, password)) {
                                return adminService;
                            } else {
                                System.out.println("Login ERROR");
                                return null;
                            }
                        case Company:
                            if (companyService.Login(email, password)) {
                                return companyService;
                            } else {
                                System.out.println("Login ERROR");
                                return null;
                            }
                        case Customer:
                            if (customerService.Login(email, password)) {
                                return customerService;
                            } else {
                                System.out.println("Login ERROR");
                                return null;
                            }
                    }
                }
            }
        }
        return instance;
    }
}
