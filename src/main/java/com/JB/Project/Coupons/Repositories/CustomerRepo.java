package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String Email);
}
