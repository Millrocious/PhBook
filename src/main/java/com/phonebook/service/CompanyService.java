package com.phonebook.service;

import com.phonebook.entity.Company;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);

    Company updateCompany(Company company);

    void deleteCompany(Company company);

    Company getCompanyById(Integer companyId);

    List<Company> getAllCompanies();

    static Company buildCompany(String name, String address, String phoneNumber) {
        Company newCompany = new Company();

        newCompany.setName(name);
        newCompany.setAddress(address);
        newCompany.setPhoneNumber(phoneNumber);

        return newCompany;
    }
}
