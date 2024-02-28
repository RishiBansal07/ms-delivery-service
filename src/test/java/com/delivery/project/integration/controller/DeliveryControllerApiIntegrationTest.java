package com.delivery.project.integration.controller;

import com.delivery.project.Application;
import com.delivery.project.dto.Order;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.entities.EmployeeDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DeliveryControllerApiIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int randomServerPort;

    private Response response;

    @Nested
    @TestInstance(PER_CLASS)
    class findAll {
        @Test
        void shouldFetchAllEmployees() {
            Long id = 3L;
            String url = "http://localhost:" + randomServerPort + "/bank/deliveryService/listOfEmployees";
            response = given().when().get(url);
            response.then().statusCode(200);
        }
    }

    @Nested
    @TestInstance(PER_CLASS)
    class placeOrder {
        @Test
        void shouldPlaceOrder() throws JsonProcessingException {
            String packageName = UUID.randomUUID().toString();
            ShippingOrderDTO shippingOrder = new ShippingOrderDTO(packageName, BigDecimal.valueOf(3000), 111L, 112L);
            EmployeeDetails employeeDetails = new EmployeeDetails(2L, 112L, "CBMouli", "11000G", "Harleem");
            Order order = new Order();
            order.setPackageName(shippingOrder.getNameOfThePackage());
            order.setPackageSize("S");
            order.setPostalCode(employeeDetails.getPostalCode());
            order.setStreetName(employeeDetails.getStreetName());
            order.setReceiverName(employeeDetails.getEmployeeName());
            String requestBody = objectMapper.writeValueAsString(shippingOrder);
            String url = "http://localhost:" + randomServerPort + "/bank/deliveryService/orderPackage";
            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .body(requestBody).post(url);
            response.then().statusCode(201);
        }
    }

        @Nested
        @TestInstance(PER_CLASS)
        class fetchOrderPlacedByEmployee {
            @Test
            void shouldfetchOrderPlacedByEmployee() throws JsonProcessingException {
                Long id = 111L;
                String url = "http://localhost:" + randomServerPort + "/bank/deliveryService/getOrderDetailsOfEmployee" + id;
                response = given().when().get(url);
                response.then().statusCode(404);
            }
        }

    @Nested
    @TestInstance(PER_CLASS)
    class fetchOrderPDetails {
        @Test
        void shouldfetchOrderPDetails() throws JsonProcessingException {
            String id = "jsuhf344n4k44";
            String url = "http://localhost:" + randomServerPort + "/bank/deliveryService/getOrderDetails" + id;
            response = given().when().get(url);
            response.then().statusCode(404);
        }
    }

}
