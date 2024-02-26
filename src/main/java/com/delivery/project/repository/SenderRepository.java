package com.delivery.project.repository;

import com.delivery.project.entity.SenderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SenderRepository extends JpaRepository<SenderDetails, Long> {

    @Query("SELECT e from SenderDetails e where e.employeeId = :employeeId")
    SenderDetails searchByEmployeeId(String employeeId);

}
