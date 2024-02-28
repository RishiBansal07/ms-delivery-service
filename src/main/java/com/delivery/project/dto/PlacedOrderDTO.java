package com.delivery.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacedOrderDTO {

private String packageId;
private String packageName;
private String packageSize;
private String postalCode;
private String streetName;
private String receiverName;
private String orderStatus;
private String expectedDeliveryDate;
private String actualDeliveryDateTime;
}
