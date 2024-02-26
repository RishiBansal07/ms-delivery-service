package com.delivery.project.repository;

import com.delivery.project.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {

    @Query("SELECT e from EmployeeDetails e where e.employeeId = :employeeId")
    EmployeeDetails searchByEmployeeId(String employeeId);

}
