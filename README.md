# Recipe API

This project allows bank employees to send packages to other employees. As disclosing address details to 
all employees within the bank raises privacy issues, in this API, you just need to mention the employee ID of receiver
and API will look out for the address. Further you can do below tasks:

1. Get list of the employees with their employee ID and address.
2. Place a new order for delivery
3. Details of all the order placed by Employee
4. Get the details of specific order

##########################################################################
### Table Definition
##########################################################################

We have created the below recipe table in reference with Instruction & Ingredient

##EmployeeDetails table:

    | ID  | employeeId | employeeName | postalCode | streetName | 

##SenderDetails table:

    | ID  | employeeId | orderIdOfPackage | packageRegisteredDate |

> - Inserted three employee details into DB using import.sql file (present in resources folder)

###########################################################################
## Design Thought
###########################################################################

```
1. Use of Simple Approach with diiferent layers of Access using Controller and Service layer.
2. There are four different API points.
3. Possible Scenarios Cover in order and Keeping Future Approach of implementation
4. JUnit Followed Mocking of Object verification using Mockito 
5. Integration tests are present in integration folder inside the test directory.
6. To Test Swagger Configured to test and verify Output of the APIs
7. Maven for Build of project and Configuration of Dependencies
```

##########################################################################
## API points
##########################################################################

The project need to expose 4 endpoints, that can talk to database layer.

| OPERATION | ENDPOINT                                                     | DESCRIPTION                                                   |
|-----------|--------------------------------------------------------------|---------------------------------------------------------------|
| GET       | /bank/deliveryService/listOfEmployees                        | Endpoint fetches all the employees with their details         |
| POST      | /bank/deliveryService/orderPackage                           | Endpoint to place a new order for delivery                    |
| GET       | /bank/deliveryService/getOrderDetailsOfEmployee/{employeeId} | Endpoint to fetch details of all the order placed by Employee |
| GET       | /bank/deliveryService//getOrderDetails/{orderId}             | Endpoint fetches the details of specific order                |


##########################################################################
## Skill set used
##########################################################################

- Java 17
- Spring
- H2
- Test Driven Development
- Junit

##########################################################################
## STEPS To Execute
##########################################################################

```
1. Import project in your workspace
2. mvn clean install
3. After Successful build, launch Spring build Application using command 
	mvn spring-boot:run or initiate your application using Intellij for Spring boot
4. once Spring boot initiated, launch Swagger using http://localhost:8085/swagger-ui.html#
5. Execute and perform your API Invocation
7. Cheers and Enjoy API Processing :)
```

## WE ARE READY?

let's start the microservice project, so by default it will start on the port localhost:8085 now!

## Swagger API

> Access Swagger API page on the URL **http://localhost:8085/swagger-ui.html**

###########################################################################
## Enhancements
###########################################################################

> - **SSL handshake connection** with the package order service API.
> - **Authorization of API**<br />
    > **Required:** Table User, every-request will go through a @PreAuthorize method that will communicate with the user
    table with the UUID coming from the JWT token or specific header value.
> - We can add more validations to field like weight of the package, keeping in sync with front end drop down options.
> - Tracing of API logs with use of ELK approach
> - Jacoco for code coverage tool - execution can be defined as Min to 80%

> - **Dockerizing the APP**<br />

> - **Using a definitive instance of DB (POSTGRES, ORACLE)**<br />

###########################################################################
## Testing
###########################################################################

```
1. Swagger
3. Junit
```

###########################################################################
## Contact Email
###########################################################################

- brishi2806@gmail.com

###########################################################################
## Thank You
###########################################################################

