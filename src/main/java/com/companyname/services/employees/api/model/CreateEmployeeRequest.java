package com.companyname.services.employees.api.model;

import com.companyname.services.employees.api.error.InvalidEmployeeException;
import lombok.Getter;

@Getter
public final class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private double salary;

    public CreateEmployeeRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidEmployeeException("First name required for new employee");
        }
        this.firstName = firstName;
        return this;
    }

    public CreateEmployeeRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidEmployeeException("Last name required for new employee");
        }
        this.lastName = lastName;
        return this;
    }

    public CreateEmployeeRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidEmployeeException("Email address required for employee");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public CreateEmployeeRequest withJobTitle(String jobTitle) {
        if (jobTitle == null || jobTitle.isEmpty()) {
            throw new InvalidEmployeeException("Job title required for new employee");
        }
        this.jobTitle = jobTitle;
        return this;
    }

    public CreateEmployeeRequest withSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            throw new InvalidEmployeeException("Salary required for new employee");
        }
        if (!salary.matches("\\d*.*\\d*")) {
            throw new InvalidEmployeeException("Invalid salary provided for employee");
        }
        this.salary = Double.parseDouble(salary);
        return this;
    }
}
