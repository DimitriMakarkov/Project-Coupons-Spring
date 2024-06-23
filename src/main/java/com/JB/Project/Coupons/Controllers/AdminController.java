package com.JB.Project.Coupons.Controllers;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
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
@RequestMapping("admin")
@CrossOrigin()
@RequiredArgsConstructor
public class AdminController {

    private final JWT jwtUtil;

    @Autowired
    CouponRepo couponRepo;
    @Autowired
    AdminService adminService;
    @Autowired
    CompanyService companyService;

    @PostMapping("/addCompany")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@Validated @RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @PutMapping("/updateCompany/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateComapany(@PathVariable int id,@RequestBody Company company) throws CouponSystemException{
        adminService.updateCompany(id,company);
    }

    @DeleteMapping("/deleteCompany/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable int id) throws CouponSystemException{
        adminService.deleteCompany(id);
    }

    @GetMapping("/getAllCompanies")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(adminService.getAllCompanies(),getHeaders(jwt),HttpStatus.OK);
    }

    @GetMapping("/getAllCoupons")
    public List<Coupon> getAllCoupons(){
        return couponRepo.findAll();
    }

    @GetMapping("/getSingleCompany/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getSingleCompany(@PathVariable int id) throws CouponSystemException {
        return adminService.getSingleCompany(id);
    }

    @GetMapping("/allCategoryCoupons/{id}")
    public List<Coupon> getAllCategoryCoupons(@PathVariable int id) throws CouponSystemException {
        return adminService.getAllCategoryCoupons(id);
    }

    private HttpHeaders getHeaders(String jwt){
        HttpHeaders headers = new HttpHeaders();
        String userJWT = jwt.split(" ")[1];
        if (jwtUtil.validateToken(userJWT)){
            headers.set("Authorization", "Bearer "+jwtUtil.generateToken(userJWT));
        }
        return headers;
    }
}
