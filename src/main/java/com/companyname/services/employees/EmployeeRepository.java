package com.companyname.services.employees;

import com.companyname.services.employees.api.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO employees (firstName, lastName, jobTitleId, salary) VALUES (:firstName, :lastName, (SELECT id FROM JobTitle jt WHERE jt.name = :jobTitle), :salary) RETURNING *")
    Employee createEmployee(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("jobTitle") String jobTitle, @Param("salary") double salary);

//    @Modifying
//    @Query("INSERT INTO Employee(firstName, lastName, jobTitleId, salary) VALUES (:firstName, :lastName, (SELECT id FROM JobTitle jt WHERE jt.name = :jobTitle), :salary)")
//    void createEmployee(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("jobTitle") String jobTitle, @Param("salary") String salary);

    @Query("SELECT NEW com.companyname.services.employees.api.EmployeeDetails(e.id, e.firstName, e.lastName, jt.name, e.salary) FROM Employee e JOIN e.jobTitle jt")
    List<EmployeeDetails> findAllEmployeeDetails();

    @Query("SELECT NEW com.companyname.services.employees.api.EmployeeDetails(e.id, e.firstName, e.lastName, jt.name, e.salary) FROM Employee e JOIN e.jobTitle jt WHERE e.id = :id")
    Optional<EmployeeDetails> findEmployeeDetailsById(@Param("id") long id);
}
