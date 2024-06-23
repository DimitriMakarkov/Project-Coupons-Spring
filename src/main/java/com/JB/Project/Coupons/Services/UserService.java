package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Credentials;
import com.JB.Project.Coupons.Beans.UserDetails;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;

public interface UserService {

    boolean registerUser(UserDetails userDetails) throws CouponSystemException;

    UserDetails loginUser(Credentials userData) throws CouponSystemException;
}
