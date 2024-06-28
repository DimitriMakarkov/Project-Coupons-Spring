package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Credentials;
import com.JB.Project.Coupons.Beans.UserDetails;
import com.JB.Project.Coupons.Beans.UserType;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.UserRepo;
import com.JB.Project.Coupons.Services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Override
    public boolean registerUser(UserDetails userDetails) throws CouponSystemException {
        if (userRepo.existsById(userDetails.getId())){
            throw new CouponSystemException(ErrorMessage.USER_EXISTS);
        }
        userRepo.save(userDetails);
        return true;
    }

    @Override
    public UserDetails loginUser(Credentials userData) throws CouponSystemException {
        if (userData.userType.name().equals("COMPANY")){
            Optional<Company> company = companyRepo.findByEmailAndPassword(userData.getEmail(),userData.getPassword());
            UserDetails userDetails = new UserDetails(company.get().getId(),company.get().getName(),company.get().getEmail(),userData.getUserType());
            System.out.println("backend data");
            System.out.println(userDetails);
            return userDetails;
        }
        if (userRepo.existsByEmailAndPassword(userData.getEmail(),userData.getPassword())){
            UserDetails userDetails = userRepo.findByEmailAndPassword(userData.getEmail(),userData.getPassword());
            System.out.println("backend data");
            System.out.println(userDetails);
            return userDetails;
        }
        throw new CouponSystemException(ErrorMessage.USER_NOT_FOUND);
    }


}
