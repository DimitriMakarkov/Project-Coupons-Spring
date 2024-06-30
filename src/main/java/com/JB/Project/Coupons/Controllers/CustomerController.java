package com.JB.Project.Coupons.Controllers;


import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
import com.JB.Project.Coupons.Services.CustomerService;
import com.JB.Project.Coupons.Utility.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin()
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JWT jwtUtil;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/purchaseCoupon/{couponID}/{customerID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> purchaseCoupon (@PathVariable int couponID,@PathVariable int customerID,@RequestHeader("Authorization") String jwt) throws CouponSystemException, ParseException {
        customerService.purchaseCoupon(couponID,customerID);
        return new ResponseEntity<>(true,getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllCustomerCoupons/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCustomerCoupons(@PathVariable int customerID,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(customerService.getAllCustomerCoupons(customerID),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCategoryCoupons/{customerID}/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCategoryCoupons(@PathVariable int customerID,@PathVariable int categoryID,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(customerService.getAllCategoryCoupons(customerID,categoryID),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllMaxPriceCoupons/{customerID}/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllMaxPriceCoupons(@PathVariable int customerID,@PathVariable float maxPrice,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(customerService.getAllMaxPriceCoupons(customerID,maxPrice),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getCustomerInfo/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCustomerInfo(@PathVariable int customerID,@RequestHeader("Authorization") String jwt) throws CouponSystemException {
        return new ResponseEntity<>(customerRepo.findById(customerID),getHeaders(jwt),HttpStatus.OK);
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
