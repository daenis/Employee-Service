package com.companyname.services.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    JobTitle findByName(String name);
}
