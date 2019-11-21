# Final Project - Enterprise Architecture

This is a microservice based project that allows users to recharge their phone bills from any service provider in any country all under one platform. It is built on the following technologies.

**Spring Security** and **JWT** for Authentication and Authorisation.
**Kubernetes** to manage containers that manage the services.
**Kubernetes Service Discovery** to locate the different services.
**Kubernetes Secret Management** to manage secrets.
**Netflix Hystrix** to manage to avoid error propagation
**Apache kafka** for message Queues
**Mongo Atlas** for cloud DB services
**Redis** for Caching
**Java Mail Sender** for sending notifications to clients
**Spring Boot**, **Spring MVC** and **Javascript**.
**Postman** to test the APIs.

# Services

This Project has been built with 8 Microservices Services.

•	Account Service
•	Service Provider Service
•	Payment Service
•	Notification Service
•	Paypal Service
•	Bank Service
•	Credit Card Service
•	Front - End Service

# Requirements
•	Apache Maven 3.6.2
•	Spring
•	Kubernetes
•	Google Cloud Platform  Cloud Shell.
•	IntelliJ or anyother IDE
•	Apache Kafka
•	Mongo Atlas
•	Redis
•	Github


### Steps
We have exposed our services with an external IP, kindly follow below steps.


##### 1. Create Account
Go to browser and hit UI Service IP Address:
```http 
http://34.67.167.114/
```
Fill in the details for opening account 

##### 2. Login
After opening an account, you will be redirected to the login page, if you already have an account, then go to browser at 
```http 
http://34.67.167.114/login
```
Login with the details **username** and **password**

##### 3. Choose Plan.
You will be required to choose country, service provider , and your desired plan.
Click Pay.

##### 4. Pay.
You will be redirected to the payments page, where you will choose from the following options.

Chose from :
•	bank
•	paypal
•	creditcard
Whichever option you choose, fill the details and click pay.

##### 5. Success and Notification.
After paying, payment will be processed by the respective services and on a successful charge, an email will be sent to your registered email containing:
•	Activated Plan
•	Plan expiry date
•	Amount of money charged.

###### NB: In case you find any issues in any of the above steps, Please get in touch with the Authors.

## Authors
###### * CHIBUISI KELVIN AMIAKA
###### * NIYONSHUTI MOSES
###### * SAROJ THAPA
###### * KEDI WAGOBERA EDGAR

## License

Copyright © 2019 [MUM](https://compro.mum.edu)
Released under the [MIT license](https://github.com/kedikebba/Phone-Bills-Payment-System/blob/add-license-1/LICENSE).

