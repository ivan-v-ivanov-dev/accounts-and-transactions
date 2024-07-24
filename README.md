# Accounts and Transactions

In this repository, I have designed a sample Accounts and Transactions microservice (Spring Web applications) application. Each person can create an account and perform a transaction.

# 1. General overview

Application runs on http://localhost:8080. This is the Gateway API service which server as Gateway to the other two services - Accounts and Tranactions. The communicate synchronously via Feign client, despite of the fact that is debatable whether the communication between Gateway API and Transaction service should be asynchronously via Kafka (after creating and account and a customer, the money transaction can be done via Kafka).

### Login 

User       

username: **user**     
password: **user1**

# 2. How to start the project
 
 - Git clone the repository and on first place start the **docker-compose.yml** file in each service - it contains the database (as official docker image).
 -  As a second step, start each service individually since each service is a standalone Spring Boot application.
 
 **Note**   
In order to start the project you need to have Docker Desktop installed on your machine. Also, the Liquibase container in each service starts, creates the tables and imports the data into the PostgreSQL container then shuts down, which is normal behavior. Despite the fact that Liquibase depends on PostgreSQL, keep an eye on it and restart it manually if needed (if and error occurs due to it's start before the postgre).

# 3. Microservice description  

**Gateway API**

Runs on http://localhost:8080. This is the service which communicates with the front end (in our case Postman). It contains a PostgresSQL database. Luquibase docker container creates the sample table and imports the sample data(see **docker-compose.yml** file).

Endpoits:
 - POST **/api/account** with RequestParam Long **customerID** and RequestParam Double **initialCredit**
 - GET **/api/customer/{customerId}** with PathVariable Long **customerId**

**Accounts**

This service creates and stores the customer and account. Runs on http://localhost:8082. It stores them in PostgresSQL database. Luquibase docker container creates the sample table and imports the sample data(see **docker-compose.yml** file).

**Transactions**

This service creates the transactions for each account(customer). Runs on http://localhost:8084. It stores them in PostgresSQL database. Luquibase docker container creates the sample table and imports the sample data(see **docker-compose.yml** file). The customer ID is included in the database for filtering purposes.

![image](https://github.com/user-attachments/assets/2d686040-4c13-4480-981d-ffc6ab5fb50b)


# 4. Use cases

**Login**

![image](https://github.com/user-attachments/assets/6e6d77e0-7f70-497f-a6e3-7c86165b2965)

![image](https://github.com/user-attachments/assets/ac8835ab-9be7-462e-869a-e82ba7109835)

(Each service has GET /healthy endpoint for testing purposes which returns **Healthy** string)

**Create Account and a Tranaction**

![image](https://github.com/user-attachments/assets/1e46b0e4-64d5-4d7f-8bb9-35bbdb2ca55e)


**View customer details**

![image](https://github.com/user-attachments/assets/aac7f843-a7c7-4a89-801c-ae06d8068805)

(When creating a customer no names are set. We might expand this functionality to reùest from the user when registering the full person details.)

Another sample user with more transactions

![image](https://github.com/user-attachments/assets/3bc8b100-c571-4598-8c31-6df4fa0a62d3)
