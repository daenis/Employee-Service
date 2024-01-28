package com.companyname.services.core.errorhandling;

import com.companyname.services.employees.api.error.InvalidEmployeeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidEmployeeException.class)
    public ResponseEntity<ApiError> handleInvalidEmployee(InvalidEmployeeException ex) {
        return ResponseEntity.badRequest().body(new ApiError(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handle(Exception ex) {
        return ResponseEntity.internalServerError().body(new ApiError(ex.getMessage()));
    }
}
