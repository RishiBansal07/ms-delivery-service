package com.delivery.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class SenderDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long employeeId;

    @NotBlank
    @Column(unique=true)
    private String orderIdOfPackage;

    private LocalDate packageRegisteredDate;

}
