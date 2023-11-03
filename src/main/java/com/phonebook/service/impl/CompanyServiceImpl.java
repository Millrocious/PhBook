package com.phonebook.service.impl;

import com.phonebook.dao.CompanyDao;
import com.phonebook.model.Company;
import com.phonebook.service.CompanyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao;

    @Override
    public void createCompany(Company company) {
        companyDao.createCompany(company);
    }

    @Override
    public Company getCompanyById(int id) {
        return companyDao.getCompanyById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    public void deleteCompany(int id) {
        companyDao.deleteCompany(id);
    }

    @Override
    public void displayCompanies(List<Company> companies) {
        System.out.println("All companies: ");
        for (Company company : companies) {
            System.out.println(company);
        }
    }
}
