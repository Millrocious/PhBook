package com.phonebook.service.impl;

import com.phonebook.entity.Company;
import com.phonebook.service.CompanyService;
import com.phonebook.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    @Override
    public Company saveCompany(Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateUtil.performTransaction(session, s -> s.persist(company));
        }

        return company;
    }

    @Override
    public Company updateCompany(Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateUtil.performTransaction(session, s -> s.merge(company));
        }

        return company;
    }

    @Override
    public void deleteCompany(Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateUtil.performTransaction(session, s -> s.remove(company));
        }
    }

    @Override
    public Company getCompanyById(Integer companyId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Company.class, companyId);
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Company", Company.class).list();
        }
    }
}
