package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon,Integer> {
}
