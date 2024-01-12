package com.companyname.services.employees.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeDetails {

    private long id;
    private String firstName;
    private String lastName;
    private String title;
    private double salary;
}
