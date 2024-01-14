package com.companyname.services.employees.api;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
public class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private double salary;

    public CreateEmployeeRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new RuntimeException("First name required for new employee");
        }
        this.firstName = firstName;
        return this;
    }

    public CreateEmployeeRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new RuntimeException("Last name required for new employee");
        }
        this.lastName = lastName;
        return this;
    }

    public CreateEmployeeRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new RuntimeException("Email address required for employee");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public CreateEmployeeRequest withJobTitle(String jobTitle) {
        if (jobTitle == null || jobTitle.isEmpty()) {
            throw new RuntimeException("Job title required for new employee");
        }
        this.jobTitle = jobTitle;
        return this;
    }

    public CreateEmployeeRequest withSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            throw new RuntimeException("Salary required for new employee");
        }
        if (!salary.matches("\\d*.*\\d*")) {
            throw new RuntimeException("Invalid salary provided for employee");
        }
        this.salary = Double.parseDouble(salary);
        return this;
    }
}
