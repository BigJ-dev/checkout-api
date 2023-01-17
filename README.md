# Checkout Api
The API is used to simplify scan items and provide types of discounts on specific items.


### Getting Started
To get the repo running for local dev the following is required:
1. Java - Install JDK 8
2. Build Tool - Project uses Maven as the build tool. No special setup required
3. Testing - JUnit has been selected as the tool for unit testing
4. Code Coverage - Jacoco has been added to pom for code coverage. Use "mvn clean install" to generate report
5. Database - H2. This will be changed when going to production
6. Script - will create the three products MUG,TSHIRT and USEKEY on start up

### Usage
1. To see all the endpoints for the api: http://localhost:8091/swagger-ui/index.html
2. Please look for the postman collection in: resources/postman-collection for easy testing

### Test Cases
1. Items: MUG, TSHIRT, USBKEY --> Total: 35.00€
2. Items: MUG, TSHIRT, MUG --> Total: 25.00€ 
3. Items: TSHIRT, TSHIRT, TSHIRT, MUG, TSHIRT --> Total: 62.80€
4. Items: MUG, TSHIRT, MUG, MUG, USBKEY, TSHIRT, TSHIRT --> Total: 62.10€
   

