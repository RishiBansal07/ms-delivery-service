package com.delivery.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
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
