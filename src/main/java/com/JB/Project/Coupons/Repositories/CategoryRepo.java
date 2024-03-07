package com.JB.Project.Coupons.Repositories;

import com.JB.Project.Coupons.Beans.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
