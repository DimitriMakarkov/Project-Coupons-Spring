package com.JB.Project.Coupons.Controllers;


import com.JB.Project.Coupons.Beans.*;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
import com.JB.Project.Coupons.Service_Implementation.UserServiceImp;
import com.JB.Project.Coupons.Services.AdminService;
import com.JB.Project.Coupons.Utility.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@CrossOrigin()
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    AdminService adminService;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    CouponRepo couponRepo;

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
public ResponseEntity<?> loginUser(@RequestBody Credentials user) throws CouponSystemException{
        System.out.println("controller "+user);
        HttpHeaders headers = new HttpHeaders();
        JWT jwt = new JWT();
        if (user.userType.name().equals("COMPANY")){
            Optional<Company> company = companyRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
            UserDetails userDetails = userServiceImp.loginUser(user);
            headers.set("Authorization",jwt.generateToken(userDetails));
            company.get().setPassword("");
            System.out.println(company);
            return new ResponseEntity<>(company,headers,HttpStatus.OK);
        } else if (user.userType.name().equals("CUSTOMER")) {
            Optional<Customer> customer = customerRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
            UserDetails userDetails = userServiceImp.loginUser(user);
            headers.set("Authorization",jwt.generateToken(userDetails));
            customer.get().setPassword("");
            System.out.println(customer);
            return new ResponseEntity<>(customer,headers,HttpStatus.OK);
        }
        UserDetails userDetails = userServiceImp.loginUser(user);
        headers.set("Authorization",jwt.generateToken(userDetails));
        userDetails.setPassword("");
//        System.out.println(userDetails);
        return new ResponseEntity<>(userDetails,headers,HttpStatus.OK);
}

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDetails data) throws Exception {
        userServiceImp.registerUser(data);
    }

    @GetMapping("/getAllCategoryCoupons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCategoryCoupons(@PathVariable int id) throws CouponSystemException {
        return adminService.getAllCategoryCoupons(id);
    }

    @GetMapping("/getAllCompanies")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping("/getAllCoupons")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCoupons(){
        return couponRepo.findAll();
    }
}
