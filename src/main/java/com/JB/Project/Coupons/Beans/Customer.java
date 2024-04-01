package com.JB.Project.Coupons.Beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 30)
    @Length(min = 3, max = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    @Length(min = 3, max = 20)
    private String lastName;

    @Column(name = "email", nullable = false, length = 30)
    @Length(min = 5, max = 20)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    @Length(min = 5, max = 20)
    private String password;

//    @OneToMany(cascade = CascadeType.MERGE)
//    @Singular
//    private List<Coupon> coupons;
}
