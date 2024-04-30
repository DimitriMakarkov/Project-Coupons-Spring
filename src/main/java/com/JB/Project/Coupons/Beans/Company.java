package com.JB.Project.Coupons.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    @Length(min = 3, max = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 30)
    @Length(min = 5, max = 20)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    @Length(min = 5, max = 20)
    private String password;

    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private List<Coupon> coupons;
//@Singular
//@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "companyid")
//private List<Coupon> coupons;
}
