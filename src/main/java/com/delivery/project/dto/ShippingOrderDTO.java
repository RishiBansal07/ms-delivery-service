package com.delivery.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ShippingOrderDTO {

        @NotBlank(message = "The field \"nameOfThePackage\" is required")
        private String nameOfThePackage;

        @NotNull(message = "The field \"weightOfThePackage\" is required")
        private BigDecimal weightOfThePackage;

        @NotNull(message = "The field \"employeeIdSender\" is required")
        private Long employeeIdSender;

        @NotNull(message = "The field \"employeeIdReceiver\" is required")
        private Long employeeIdReceiver;
}
