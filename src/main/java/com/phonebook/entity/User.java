package com.phonebook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Company company;
}