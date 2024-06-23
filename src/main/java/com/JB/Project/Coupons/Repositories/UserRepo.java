package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDetails,Integer> {
    UserDetails findByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);
}
