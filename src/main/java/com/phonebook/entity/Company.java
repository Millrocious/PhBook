package com.phonebook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Company {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private Set<User> users;
}
