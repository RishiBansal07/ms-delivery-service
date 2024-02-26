package com.delivery.project.dto;

import lombok.Data;

@Data
public class PlacedOrder {

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
