package com.companyname.services.core.users.api.model;

import lombok.Getter;

// TODO: Update exceptions
@Getter
public final class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserRegistrationRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new RuntimeException("First name is required to register for an account");
        }
        this.firstName = firstName;
        return this;
    }

    public UserRegistrationRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new RuntimeException("Last name is required to register for an account");
        }
        this.lastName = lastName;
        return this;
    }

    public UserRegistrationRequest withEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email is required to register for an account");
        }
        this.email = email;
        return this;
    }

    public UserRegistrationRequest withPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Password is required to register for an account");
        }
        this.password = password;
        return this;
    }

    public UserRegistrationRequest confirmPassword(String password) {
        if (password == null || password.isEmpty() || !password.equals(this.password)) {
            throw new RuntimeException("Passwords do not match");
        }
        return this;
    }
}
