package com.phonebook;

import com.phonebook.dao.CompanyDao;
import com.phonebook.dao.UserDao;
import com.phonebook.model.Company;
import com.phonebook.model.User;
import com.phonebook.service.CompanyService;
import com.phonebook.service.UserService;
import com.phonebook.service.impl.CompanyServiceImpl;
import com.phonebook.service.impl.UserServiceImpl;
import com.phonebook.util.JdbcUtil;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JdbcUtil.init();

        try (Connection connection = JdbcUtil.getConnection()) {
            // Users
            UserDao userDao = new UserDao(connection);
            UserService userService = new UserServiceImpl(userDao);

            // Build user
            User newUser = UserService.buildUser("Max", "Shell", "123-123-1234");
            User newUser2 = UserService.buildUser("Levi", "Muller", "988-777-6666");

            // Create user
            userService.createUser(newUser);
            userService.createUser(newUser2);

            // Get user
            User retrievedUser = userService.getUserById(3);
            System.out.println("Retrieved user: " + retrievedUser);

            User retrievedUser2 = userService.getUserById(4);
            System.out.println("Retrieved user: " + retrievedUser2);

            // Get users
            List<User> allUsers = userService.getAllUsers();
            userService.displayUsers(allUsers);

            // Update user
            retrievedUser.setPhoneNumber("555-999-8888");
            userService.updateUser(retrievedUser);
            System.out.println("Updated user: " + retrievedUser);

            // Delete user
            int userIdToDelete = 4;
            userService.deleteUser(userIdToDelete);
            System.out.println("Deleted user with ID " + userIdToDelete);

            userService.displayUsers(userService.getAllUsers());

            // ---------
            // Companies
            CompanyDao companyDao = new CompanyDao(connection);
            CompanyService companyService = new CompanyServiceImpl(companyDao);

            // Build company
            Company newCompany = CompanyService.buildCompany("Example Corp", "23 Main St", "555-123-4567");
            Company newCompany2 = CompanyService.buildCompany("Example Corp test 2", "67 Main St", "666-123-9876");

            // Create company
            companyService.createCompany(newCompany);
            companyService.createCompany(newCompany2);

            // Get Company
            Company retrievedCompany = companyService.getCompanyById(3);
            System.out.println("Retrieved company: " + retrievedCompany);

            Company retrievedCompany2 = companyService.getCompanyById(4);
            System.out.println("Retrieved company: " + retrievedCompany2);

            // Get companies
            List<Company> allCompanies = companyService.getAllCompanies();
            companyService.displayCompanies(companyService.getAllCompanies());

            // Update company
            retrievedCompany.setPhoneNumber("555-999-8888");
            companyService.updateCompany(retrievedCompany);
            System.out.println("Updated company: " + retrievedCompany);

            // Delete company
            int companyIdToDelete = 4;
            companyService.deleteCompany(companyIdToDelete);
            System.out.println("Deleted company with ID " + companyIdToDelete);

            companyService.displayCompanies(companyService.getAllCompanies());

        } catch (Exception e) {
            System.out.println(e);
        }

        JdbcUtil.clearData();
    }
}