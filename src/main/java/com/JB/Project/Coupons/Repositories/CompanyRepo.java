package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company,Integer> {

    Optional<Company> findByName(String Name);

    Optional<Company> findByEmail(String Email);

}
