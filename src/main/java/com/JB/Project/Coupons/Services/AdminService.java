package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import java.util.List;

public interface AdminService {

    boolean Login(String email,String password);

    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int companyID,Company company) throws CouponSystemException;

    void deleteCompany(int companyID) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getSingleCompany(int companyID) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(int customerID,Customer customer) throws  CouponSystemException;

    void deleteCustomer(int customerID) throws CouponSystemException;

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int customerID) throws CouponSystemException;


}
