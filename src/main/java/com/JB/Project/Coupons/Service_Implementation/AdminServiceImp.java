package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    CompanyRepo companyRepo;

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        Optional<Company> findCompany = companyRepo.findByName(company.getName());
//        findCompany.ifPresent((comp) -> {
//            System.out.println("Company with the same name already exists");
//        });
        if (!findCompany.isPresent()) {
            companyRepo.save(company);
            System.out.println("Company saved successfully!");
        }
        else {
            System.out.println("Company with the same name already exists");
        }
    }

    @Override
    public void updateCompany(int companyID, Company company) throws CouponSystemException {

    }

    @Override
    public void deleteCompany(int companyID) throws CouponSystemException {

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getSingleCompany(int companyID) throws CouponSystemException {
        return companyRepo.findById(companyID).orElseThrow(() -> new CouponSystemException(ErrorMessage.ID_NOT_FOUND));
    }
}
