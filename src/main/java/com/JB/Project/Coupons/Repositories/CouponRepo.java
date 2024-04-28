package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon,Integer> {

    Optional<Coupon> findByTitle(String Name);

    List<Coupon> findAllById(int company_id);

    List<Coupon> findAllByCompanyid(int Companyid);
}
