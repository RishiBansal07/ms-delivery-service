package com.delivery.project.services.interfaces;

import com.delivery.project.entities.EmployeeDetails;

import java.util.List;

public interface EmployeeService {

    void saveEmployeeDetails(EmployeeDetails employeeDetails);
    EmployeeDetails searchByEmployeeId(Long id);
    List<EmployeeDetails> getListOfEmployee();

}
