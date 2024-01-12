package com.companyname.services.employees;

import com.companyname.services.employees.api.EmployeeDetails;
import com.companyname.services.employees.api.FindAllEmployees;
import com.companyname.services.employees.api.FindEmployeeById;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
class EmployeeService implements FindAllEmployees, FindEmployeeById {

    @Override
    public List<EmployeeDetails> execute() {
        EmployeeDetails employeeDetails = new EmployeeDetails(1, "Jon", "Doe", "Chef", 90000.00);
        return Collections.singletonList(employeeDetails);
    }

    @Override
    public EmployeeDetails executeFor(long id) {
        return new EmployeeDetails(1, "Jon", "Doe", "Chef", 90000.00);
    }
}
