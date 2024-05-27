package com.JB.Project.Coupons.Service_Implementation;

import com.JB.Project.Coupons.Beans.Company;
import com.JB.Project.Coupons.Beans.Coupon;
import com.JB.Project.Coupons.Beans.Customer;
import com.JB.Project.Coupons.Exceptions.CouponSystemException;
import com.JB.Project.Coupons.Exceptions.ErrorMessage;
import com.JB.Project.Coupons.Repositories.CompanyRepo;
import com.JB.Project.Coupons.Repositories.CouponRepo;
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
    CouponRepo couponRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public boolean Login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            return true;
        }
        return false;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        Optional<Company> findCompanyName = companyRepo.findByName(company.getName());
        if (!findCompanyName.isPresent()) {
            Optional<Company> findCompanyEmail = companyRepo.findByEmail(company.getEmail());
            if (!findCompanyEmail.isPresent()) {
                System.out.println(company);
                companyRepo.save(company);
                System.out.println("Company saved successfully!");
            } else {
                System.out.println("Company with the same email already exists...");
                throw new CouponSystemException(ErrorMessage.COMPANY_EMAIL_EXISTS);
            }
        } else {
            System.out.println("Company with the same name already exists...");
            throw new CouponSystemException(ErrorMessage.COMPANY_NAME_EXISTS);
        }
    }

    @Override
    public void updateCompany(int companyID, Company company) throws CouponSystemException {
        Optional<Company> findCompany = companyRepo.findById(companyID);
        if (findCompany.isPresent()) {
            if (company.getName().equals(findCompany.get().getName())) {
                System.out.println(company);
                companyRepo.saveAndFlush(company);
                System.out.println("Company has been updated!");
            } else {
                System.out.println("Cannot change company name...");
                throw new CouponSystemException(ErrorMessage.COMPANY_CANNOT_CHANGE_NAME);
            }
        } else {
            System.out.println("Company not found...");
            throw new CouponSystemException(ErrorMessage.COMPANY_NOT_FOUND);
        }
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        Optional<Company> findCompany = companyRepo.findById(companyId);
        System.out.println(findCompany);
        if (findCompany.isPresent()) {
            List<Customer> CustomersWithCoupons = customerRepo.findByCouponsCompanyid(companyId);
            System.out.println(CustomersWithCoupons);
            if (!CustomersWithCoupons.isEmpty()) {
                for (Customer customer : CustomersWithCoupons) {
                    customer.setCoupons(null);
                    System.out.println(CustomersWithCoupons);
                }
                customerRepo.saveAllAndFlush(CustomersWithCoupons);
                couponRepo.deleteByCompanyid(companyId);
                companyRepo.deleteById(companyId);

            }
        } else {
            System.out.println("Company not found...");
            throw new CouponSystemException(ErrorMessage.COMPANY_NOT_FOUND);
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
            System.out.println(customer);
            customerRepo.save(customer);
            System.out.println("Customer saved successfully!");
        } else {
            System.out.println("Customer with the same email already exists...");
            throw new CouponSystemException(ErrorMessage.CUSTOMER_EMAIL_EXISTS);
        }
    }

    @Override
    public void updateCustomer(int customerID, Customer customer) throws CouponSystemException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            if (customer.getId().equals(findCustomer.get().getId())) {
                System.out.println(customer);
                customerRepo.saveAndFlush(customer);
                System.out.println("Customer has been updated!");
            } else {
                System.out.println("Customer id does not match...");
            }
        } else {
            System.out.println("Customer not found...");
            new CouponSystemException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        Optional<Customer> findCustomer = customerRepo.findById(customerID);
        if (findCustomer.isPresent()) {
            Customer customer = findCustomer.get();
            System.out.println(customer);
            customer.setCoupons(null);
            customerRepo.saveAndFlush(customer);
            customerRepo.deleteById(customerID);
            System.out.println("Customer has been deleted!");
        } else {
            System.out.println("Customer not found...");
        }
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
