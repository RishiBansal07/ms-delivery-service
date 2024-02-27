package com.delivery.project.repository;

import com.delivery.project.entity.SenderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SenderRepository extends JpaRepository<SenderDetails, Long> {

    @Query("SELECT e from SenderDetails e where e.employeeId = :employeeId")
    List<SenderDetails> searchByEmployeeId(String employeeId);

    @Query("SELECT o from SenderDetails o where o.orderIdOfPackage = :orderId")
    SenderDetails searchByOrderId(String orderId);

}
