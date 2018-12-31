package com.foureverinbeta.employeemanagementservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}

