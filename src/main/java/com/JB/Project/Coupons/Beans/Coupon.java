package com.JB.Project.Coupons.Beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_id",nullable = false)
    @Length(min = 0)
    private Integer company_id;

    @Column(name = "category_id",nullable = false)
    @Length(min = 0)
    private Integer category_id;

    @Column(name = "title",nullable = false,length = 25)
    @Length(min = 3,max = 20)
    private String title;

    @Column(name = "description",nullable = false,length = 40)
    @Length(min = 0,max = 40)
    private String description;

    @Column(name = "start_date",nullable = false)
    private Date start_date; //Date mysql not java util

    @Column(name = "end_date",nullable = false)
    private Date end_date; //Date mysql not java util

    @Column(name = "amount",nullable = false)
    @Length(min = 0)
    private Integer amount;

    @Column(name = "price",nullable = false)
    @Length(min = 0)
    private Double price;

    @Column(name = "image",nullable = true)
    private String image;

    @ManyToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Coupon> coupons;
}
