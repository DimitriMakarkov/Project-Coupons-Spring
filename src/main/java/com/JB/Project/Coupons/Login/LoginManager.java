package com.JB.Project.Coupons.Login;

import com.JB.Project.Coupons.Service_Implementation.AdminServiceImp;
import com.JB.Project.Coupons.Service_Implementation.CompanyServiceImp;
import com.JB.Project.Coupons.Service_Implementation.CustomerServiceImp;
import com.JB.Project.Coupons.Services.AdminService;
import com.JB.Project.Coupons.Services.CompanyService;
import com.JB.Project.Coupons.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {

    private static LoginManager instance;

    private LoginManager() {
    }

    @Autowired
    AdminService adminService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CustomerService customerService;

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    public AdminService AdminLogin(String Email, String Password, ClientType Type) {
        switch (Type) {
            case Admin:
                AdminService admin = new AdminServiceImp();
                if (admin.Login(Email, Password)) {
                    System.out.println("Admin logged in!");
                    return adminService;
                } else {
                    System.out.println("Login ERROR");
                }
        }
        return null;
    }

    public CompanyService CompanyLogin(String Email, String Password, ClientType Type) {
        switch (Type) {
            case Admin:
                CompanyService company = new CompanyServiceImp();
                if (company.Login(Email, Password)) {
                    System.out.println("Company logged in!");
                    return companyService;
                } else {
                    System.out.println("Login ERROR");
                }
        }
        return null;
    }

    public CustomerService CustomerLogin(String Email, String Password, ClientType Type) {
        switch (Type) {
            case Admin:
                CustomerService customer = new CustomerServiceImp();
                if (customer.Login(Email, Password)) {
                    System.out.println("Customer logged in!");
                    return customerService;
                } else {
                    System.out.println("Login ERROR");
                }
        }
        return null;
    }
}
