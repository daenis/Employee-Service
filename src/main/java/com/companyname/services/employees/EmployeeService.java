package com.companyname.services.employees;

import com.companyname.services.employees.api.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
final class EmployeeService implements CreateEmployee, FindAllEmployees, FindEmployeeById {

    private final EmployeeRepository employeeRepository;
    private final JobTitleRepository jobTitleRepository;

    @Override
    public EmployeeDetails executeFor(CreateEmployeeRequest theRequest) {
        Employee employee = new Employee();
        employee.setFirstName(theRequest.getFirstName());
        employee.setLastName(theRequest.getLastName());
        employee.setJobTitle(jobTitleRepository.findByName(theRequest.getJobTitle()));
        employee.setSalary(theRequest.getSalary());
        return employeeRepository.save(employee).getDetails();
    }

    @Override
    public List<EmployeeDetails> execute() {
        return employeeRepository.findAllEmployeeDetails();
    }

    @Override
    public EmployeeDetails executeFor(long id) {
        return employeeRepository.findEmployeeDetailsById(id).orElseThrow(() -> new RuntimeException("Error retrieving employee details"));
    }
}
