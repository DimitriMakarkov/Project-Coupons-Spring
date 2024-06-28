package com.JB.Project.Coupons.Controllers;

import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Credentials;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Services.CompanyService;
import com.JB.Project.Coupons.Utility.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final JWT jwtUtil;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepo companyRepo;

    @PostMapping("/addCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCoupon(@Validated @RequestBody Coupon coupon,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        companyService.addCoupon(coupon);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.CREATED);
    }

    @PutMapping("/updateCoupon/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCoupon(@PathVariable int id,@RequestBody Coupon coupon,@RequestHeader("Authorization") String jwt) throws CouponSystemException{
        companyService.updateCoupon(id,coupon);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCoupon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteCoupon(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException{
        companyService.deleteCoupon(id);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCompanyCoupons")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCompanyCoupons(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(companyService.getAllCompanyCoupons(id),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCategoryCoupons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCategoryCoupons(@PathVariable int companyID,int categoryID,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(companyService.getAllCategoryCoupons(companyID,categoryID),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllMaxPriceCoupons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllMaxPriceCoupons(@PathVariable int companyID,float maxPrice,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(companyService.getAllMaxPriceCoupons(companyID,maxPrice),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getCompanyInfo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCompanyInfo(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(companyRepo.findById(id),getHeaders(jwt),HttpStatus.OK);
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
