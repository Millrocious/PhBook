package com.phonebook.service;

import com.phonebook.model.Company;

import java.util.List;

public interface CompanyService {
    void createCompany(Company company);

    Company getCompanyById(int id);

    List<Company> getAllCompanies();

    void updateCompany(Company company);

    void deleteCompany(int id);

    void displayCompanies(List<Company> companies);

    static Company buildCompany(String name, String address, String phoneNumber) {
        return Company.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
    }
}
