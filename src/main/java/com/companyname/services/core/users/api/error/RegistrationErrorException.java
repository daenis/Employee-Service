package com.companyname.services.core.users.api.error;

public class RegistrationErrorException extends RuntimeException {

    public RegistrationErrorException(String message) {
        super(message);
    }

    public RegistrationErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
