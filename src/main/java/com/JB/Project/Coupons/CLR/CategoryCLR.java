package com.JB.Project.Coupons.CLR;

import com.JB.Project.Coupons.Beans.Category;
import com.JB.Project.Coupons.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
@Order(3)
public class CategoryCLR implements CommandLineRunner {

    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public void run(String... args) throws Exception {

        Category category1 = Category.builder()
                .name("Food")
                .build();

        Category category2 = Category.builder()
                .name("Electricity")
                .build();

        Category category3 = Category.builder()
                .name("Restaurant")
                .build();

        Category category4 = Category.builder()
                .name("Vacation")
                .build();

        categoryRepo.saveAll(Arrays.asList(category1,category2,category3,category4));
    }
}
