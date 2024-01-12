package com.companyname.services.employees.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public final class EmployeeController {

    private final FindAllEmployees findAllEmployees;
    private final FindEmployeeById findEmployeeById;

    @GetMapping("/v1/employees")
    public ResponseEntity<List<EmployeeDetails>> findAll() {
        return ResponseEntity.ok(findAllEmployees.execute());
    }

    @GetMapping("/v1/employees/{id}")
    public ResponseEntity<EmployeeDetails> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(findEmployeeById.executeFor(id));
    }
}
