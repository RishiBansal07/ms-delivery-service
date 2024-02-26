package com.delivery.project.services;

import com.delivery.project.entity.EmployeeDetails;

import java.util.List;

public interface EmployeeService {

    void saveEmployeeDetails(EmployeeDetails employeeDetails);

    EmployeeDetails searchByEmployeeId(String id);

    List<EmployeeDetails> getListOfEmployee();

}
