package com.JB.Project.Coupons.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(name = "companyId",nullable = false)
    private Integer companyid;

    @Column(name = "categoryId",nullable = false)
    private Integer categoryid;

    @Column(name = "title",nullable = false,length = 25)
    @Length(min = 3,max = 20)
    private String title;

    @Column(name = "description",nullable = false,length = 40)
    @Length(min = 0,max = 40)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd") //new
    @Column(name = "start_date",nullable = false)
    private Date start_date; //Date mysql not java util

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd") //new
    @Column(name = "end_date",nullable = false)
    private Date end_date; //Date mysql not java util

    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name = "price",nullable = false)
    private float price;

    @Column(name = "image")
    private String image;

//    @ManyToMany(cascade = CascadeType.REMOVE)
//    @Singular
//    private List<Coupon> coupons;
//@Singular
//@OneToMany(cascade = CascadeType.ALL)
//    private List<Customer> customers;


}
