package com.phonebook.service;

import com.phonebook.entity.Company;
import com.phonebook.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    User getUserById(Integer userId);

    List<User> getAllUsers();

    static User buildUser(String firstName, String lastName, String phoneNumber, Company company) {
        User newUser = new User();

        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setCompany(company);

        return newUser;
    }
}
