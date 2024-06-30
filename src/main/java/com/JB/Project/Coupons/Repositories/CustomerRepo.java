package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String Email);

    List<Customer> findByCouponsCompanyid(Integer companyid);

    Optional<Customer> findByEmailAndPassword(String Email, String Password);

    List<Customer> findByCouponsId(Integer couponid);
}
