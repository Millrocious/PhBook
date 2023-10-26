package com.phonebook.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
}
