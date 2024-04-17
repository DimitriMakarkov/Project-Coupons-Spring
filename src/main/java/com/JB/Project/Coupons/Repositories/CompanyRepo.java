package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company,Integer> {

    Optional<Company> findByName(String Name);

    Optional<Company> findByEmail(String Email);

}
