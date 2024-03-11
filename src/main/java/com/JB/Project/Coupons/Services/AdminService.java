package com.JB.Project.Coupons.Services;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import java.util.List;

public interface AdminService {

    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int companyID,Company company) throws CouponSystemException;

    void deleteCompany(int companyID) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getSingleCompany(int companyID) throws CouponSystemException;
}
