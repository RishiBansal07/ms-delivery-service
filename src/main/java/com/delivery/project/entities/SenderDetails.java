package com.delivery.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
