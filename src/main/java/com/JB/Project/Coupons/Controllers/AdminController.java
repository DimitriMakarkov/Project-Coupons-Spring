package com.JB.Project.Coupons.Controllers;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Repositories.CouponRepo;
import com.JB.Project.Coupons.Services.AdminService;
import com.JB.Project.Coupons.Services.CompanyService;
import com.JB.Project.Coupons.Utility.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin()
@RequiredArgsConstructor
public class AdminController {

    private final JWT jwtUtil;

    @Autowired
    CouponRepo couponRepo;
    @Autowired
    AdminService adminService;


    @PostMapping("/addCompany")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCompany(@Validated @RequestBody  Company company,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        adminService.addCompany(company);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.CREATED);
    }

    @PutMapping("/updateCompany/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCompany(@PathVariable int id,@RequestBody Company company,@RequestHeader("Authorization") String jwt) throws CouponSystemException{
        adminService.updateCompany(id,company);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCompany/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteCompany(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException{
        adminService.deleteCompany(id);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCompanies")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(adminService.getAllCompanies(),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCoupons")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCoupons(@RequestHeader("Authorization") String jwt){
        System.out.println(jwt);
        return new ResponseEntity<>(couponRepo.findAll(),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getSingleCompany/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSingleCompany(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(adminService.getSingleCompany(id),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCategoryCoupons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCategoryCoupons(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(adminService.getAllCategoryCoupons(id),getHeaders(jwt),HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCustomer(@Validated @RequestBody Customer customer,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        adminService.addCustomer(customer);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCustomer(@PathVariable int id,@RequestBody Customer customer,@RequestHeader("Authorization") String jwt) throws CouponSystemException{
        adminService.updateCustomer(id,customer);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCustomer(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException{
        adminService.deleteCustomer(id);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllCustomers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(adminService.getAllCustomers(),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getSingleCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSingleCustomer(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(adminService.getSingleCustomer(id),getHeaders(jwt),HttpStatus.OK);
    }



    private HttpHeaders getHeaders(String jwt){
        HttpHeaders headers = new HttpHeaders();
//        String userJWT = jwt.split(" ")[1];
        if (jwtUtil.validateToken(jwt)){
            headers.set("Authorization",jwtUtil.generateToken(jwt));
        }
        return headers;
    }
}
