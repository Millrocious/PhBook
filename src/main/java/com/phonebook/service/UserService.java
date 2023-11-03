package com.phonebook.service;

import com.phonebook.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    User getUserById(int userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int id);

    void displayUsers(List<User> users);

    static User buildUser(String firstName, String lastName, String phoneNumber) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
    }
}
