package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.CustomerRepo;
import com.JB.Project.Coupons.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        Optional<Company> findCompanyName = companyRepo.findByName(company.getName());
        if (!findCompanyName.isPresent()) {
            Optional<Company> findCompanyEmail = companyRepo.findByEmail(company.getEmail());
            if (!findCompanyEmail.isPresent()){
                companyRepo.save(company);
                System.out.println("Company saved successfully!");
            }
            else {
                System.out.println("Company with the same email already exists");
                throw new CouponSystemException(ErrorMessage.COMPANY_EMAIL_EXISTS);
            }
        }
        else {
            System.out.println("Company with the same name already exists");
            throw new CouponSystemException(ErrorMessage.COMPANY_NAME_EXISTS);
        }
    }

    @Override
    public void updateCompany(int companyID, Company company) throws CouponSystemException {
        Optional<Company> findCompany = companyRepo.findById(companyID);
        if (findCompany.isPresent()) {
            companyRepo.saveAndFlush(company); //works only if inputs id in body
            System.out.println("Company has been updated!");
        }
        else {
            System.out.println("Company not found");
        }
    }

    @Override
    public void deleteCompany(int companyID) throws CouponSystemException {
        //deletes the associated customer to the coupon that is associated to the company
        Optional<Company> findCompany = companyRepo.findById(companyID);
        if (findCompany.isPresent()) {
            companyRepo.deleteById(companyID);
            System.out.println("Company has been deleted!");
        }
        else {
            System.out.println("Company not found");
            new CouponSystemException(ErrorMessage.ID_NOT_FOUND);
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getSingleCompany(int companyID) throws CouponSystemException {
        return companyRepo.findById(companyID).orElseThrow(() -> new CouponSystemException(ErrorMessage.ID_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        Optional<Customer> findCustomerName = customerRepo.findByEmail(customer.getEmail());
        if (!findCustomerName.isPresent()) {
                customerRepo.save(customer);
                System.out.println("Customer saved successfully!");
            }
            else {
                System.out.println("Customer with the same email already exists");
                throw new CouponSystemException(ErrorMessage.CUSTOMER_EMAIL_EXISTS);
            }
        }

    @Override
    public void updateCustomer(int customerID, Customer customer) throws CouponSystemException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            customerRepo.saveAndFlush(customer);
            System.out.println("Customer has been updated!");
        } else {
            System.out.println("Customer not found");
            new CouponSystemException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        //todo: delete with all purchased coupons
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerID) throws CouponSystemException {
        return customerRepo.findById(customerID).orElseThrow(() -> new CouponSystemException(ErrorMessage.CUSTOMER_NOT_FOUND));
    }
}
