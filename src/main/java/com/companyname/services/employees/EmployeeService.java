package com.companyname.services.employees;

import com.companyname.services.employees.api.EmployeeDetails;
import com.companyname.services.employees.api.FindAllEmployees;
import com.companyname.services.employees.api.FindEmployeeById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
final class EmployeeService implements FindAllEmployees, FindEmployeeById {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDetails> execute() {
        return employeeRepository.findAllEmployeeDetails();
    }

    @Override
    public EmployeeDetails executeFor(long id) {
        return employeeRepository.findEmployeeDetailsById(id).orElseThrow(() -> new RuntimeException("Error retrieving employee details"));
    }
}
