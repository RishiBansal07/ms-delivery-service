package com.delivery.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique=true)
    private Long employeeId;

    @NotNull
    private String employeeName;

    @NotNull
    private String postalCode;

    @NotNull
    private String streetName;
}
