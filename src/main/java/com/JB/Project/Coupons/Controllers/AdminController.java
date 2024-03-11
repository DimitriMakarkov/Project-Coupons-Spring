package com.JB.Project.Coupons.Controllers;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public List<Company> getAllCompanies(){
        return adminService.getAllCompanies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@Validated @RequestBody Company company) throws CouponSystemException{
        adminService.addCompany(company);
    }
}
