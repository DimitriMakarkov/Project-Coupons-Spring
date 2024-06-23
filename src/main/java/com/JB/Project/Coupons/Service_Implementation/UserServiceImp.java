package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Credentials;
import com.JB.Project.Coupons.Beans.UserDetails;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.UserRepo;
import com.JB.Project.Coupons.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo userRepo;

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
        if (userRepo.existsByEmailAndPassword(userData.getEmail(),userData.getPassword())){
            UserDetails userDetails = userRepo.findByEmailAndPassword(userData.getEmail(),userData.getPassword());
            System.out.println("backend data");
            System.out.println(userDetails);
            return userDetails;
        }
        throw new CouponSystemException(ErrorMessage.USER_NOT_FOUND);
    }


}
