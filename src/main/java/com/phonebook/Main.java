package com.phonebook;

import com.phonebook.entity.Company;
import com.phonebook.entity.User;
import com.phonebook.service.CompanyService;
import com.phonebook.service.UserService;
import com.phonebook.service.impl.CompanyServiceImpl;
import com.phonebook.service.impl.UserServiceImpl;
import com.phonebook.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        CompanyService companyService = new CompanyServiceImpl();


        Company newCompany = CompanyService.buildCompany("Example Corp", "23 Main St", "555-123-4567");
        Company newCompany2 = CompanyService.buildCompany("Example Corp test 2", "67 Main St", "666-123-9876");
        // Create company
        Company savedCompany = companyService.saveCompany(newCompany);
        Company savedCompany2 = companyService.saveCompany(newCompany2);

        User newUser = UserService.buildUser("Max", "Shell", "123-123-1234", newCompany);
        User newUser2 = UserService.buildUser("Levi", "Muller", "988-777-6666", newCompany);

        // Create user
        User savedUser = userService.saveUser(newUser);
        User savedUser2 = userService.saveUser(newUser2);

        // Get user
        User retrievedUser = userService.getUserById(savedUser.getId());
        System.out.println("Retrieved user: " + retrievedUser);

        User retrievedUser2 = userService.getUserById(savedUser2.getId());
        System.out.println("Retrieved user: " + retrievedUser2);

        // Get users
        System.out.println("List of all users");
        userService.getAllUsers().forEach(System.out::println);

        // Update user
        retrievedUser.setPhoneNumber("555-999-8888");
        User updatedUser = userService.updateUser(retrievedUser);
        System.out.println("Updated user: " + updatedUser);

        // Delete user
        userService.deleteUser(retrievedUser2);
        System.out.println("User deleted");

        userService.getAllUsers().forEach(System.out::println);

        // Get Company
        Company retrievedCompany = companyService.getCompanyById(savedCompany.getId());
        System.out.println("Retrieved company: " + retrievedCompany);

        Company retrievedCompany2 = companyService.getCompanyById(savedCompany2.getId());
        System.out.println("Retrieved company: " + retrievedCompany2);

        // Get companies
        System.out.println("List of all companies");
        companyService.getAllCompanies().forEach(System.out::println);

        // Update company
        retrievedCompany.setPhoneNumber("555-999-8888");
        Company updatedCompany = companyService.updateCompany(retrievedCompany);
        System.out.println("Updated company: " + updatedCompany);

        // Delete company
        companyService.deleteCompany(retrievedCompany2);
        System.out.println("Company deleted");

        companyService.getAllCompanies().forEach(System.out::println);


        HibernateUtil.shutdown();
    }
}