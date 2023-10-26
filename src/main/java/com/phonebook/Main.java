package com.phonebook;

import com.phonebook.dao.CompanyDao;
import com.phonebook.dao.UserDao;
import com.phonebook.model.Company;
import com.phonebook.model.User;
import com.phonebook.util.JdbcUtil;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JdbcUtil.init();

        try (Connection connection = JdbcUtil.getConnection()) {
            // Users
            UserDao userDao = new UserDao(connection);

            User newUser = User.builder()
                    .firstName("Max")
                    .lastName("Shell")
                    .phoneNumber("123-123-1234")
                    .build();

            User newUser2 = User.builder()
                    .firstName("Levi")
                    .lastName("Muller")
                    .phoneNumber("988-777-6666")
                    .build();

            userDao.createUser(newUser);
            userDao.createUser(newUser2);

            User retrievedUser = userDao.getUserById(3);
            System.out.println("Retrieved user: " + retrievedUser);

            User retrievedUser2 = userDao.getUserById(4);
            System.out.println("Retrieved user: " + retrievedUser2);

            List<User> allUsers = userDao.getAllUsers();
            System.out.println("All users: ");
            for (User user : allUsers) {
                System.out.println(user);
            }

            retrievedUser.setPhoneNumber("555-999-8888");
            userDao.updateUser(retrievedUser);
            System.out.println("Updated user: " + retrievedUser);

            int userIdToDelete = 4;
            userDao.deleteUser(userIdToDelete);
            System.out.println("Deleted user with ID " + userIdToDelete);

            System.out.println("All users: ");
            for (User user : userDao.getAllUsers()) {
                System.out.println(user);
            }

            // Companies
            CompanyDao companyDao = new CompanyDao(connection);

            Company newCompany = Company.builder()
                    .name("Example Corp")
                    .address("23 Main St")
                    .phoneNumber("555-123-4567")
                    .build();

            Company newCompany2 = Company.builder()
                    .name("Example Corp test 2")
                    .address("67 Main St")
                    .phoneNumber("666-123-9876")
                    .build();

            companyDao.createCompany(newCompany);
            companyDao.createCompany(newCompany2);

            Company retrievedCompany = companyDao.getCompanyById(3);
            System.out.println("Retrieved company: " + retrievedCompany);

            Company retrievedCompany2 = companyDao.getCompanyById(4);
            System.out.println("Retrieved company: " + retrievedCompany2);

            List<Company> allCompanies = companyDao.getAllCompanies();
            System.out.println("All companies: ");
            for (Company company : allCompanies) {
                System.out.println(company);
            }

            retrievedCompany.setPhoneNumber("555-999-8888");
            companyDao.updateCompany(retrievedCompany);
            System.out.println("Updated company: " + retrievedCompany);

            int companyIdToDelete = 4;
            companyDao.deleteCompany(companyIdToDelete);
            System.out.println("Deleted company with ID " + companyIdToDelete);

            System.out.println("All companies: ");
            for (Company company : companyDao.getAllCompanies()) {
                System.out.println(company);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        JdbcUtil.clearData();
    }
}