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

//    @Autowired
//    AdminService adminService;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addCompany(@Validated @RequestBody Company company) throws CouponSystemException {
//        adminService.addCompany(company);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void updateComapany(@PathVariable int id,@RequestBody Company company) throws CouponSystemException{
//        adminService.updateCompany(id,company);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void deleteCompany(@PathVariable int id) throws CouponSystemException{
//        adminService.deleteCompany(id);
//    }
//
//    @GetMapping
//    public List<Company> getAllCompanies() {
//        return adminService.getAllCompanies();
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Company getSingleCompany(@PathVariable int id) throws CouponSystemException {
//        return adminService.getSingleCompany(id);
//    }
}
