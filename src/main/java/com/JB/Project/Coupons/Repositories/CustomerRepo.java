package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
