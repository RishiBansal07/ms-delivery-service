package com.delivery.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShippingOrderDTO {

        private String nameOfThePackage;

        private String weightOfThePackage;

        private String employeeIdSender;

        private String employeeIdReceiver;
}
