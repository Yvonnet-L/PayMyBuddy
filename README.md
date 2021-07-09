PayMyBuddy  V1.0
========================
![Java Version](https://img.shields.io/badge/Java-1.8-blue)
![Maven Version](https://img.shields.io/badge/Maven-2.7.7-yellow)
![SpringBoot Version](https://img.shields.io/badge/Spring%20Boot-2.4.3-red)
![MySQL](https://img.shields.io/badge/MySQL--cyan)
![TomCat](https://img.shields.io/badge/TomCat:8080-9.0.41-brightgreen)

Description
------------
Pay My Buddy is an application that allows you to easily transfer money to your friends to facilitate the organization of events

## Prerequisites
### Technologies
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
### Installing

Here are the links for the prerequisites necessary for proper operation. In addition.
    
 Install Java:
* https://www.oracle.com/fr/java/technologies/javase-downloads.html

Install Maven:
* https://maven.apache.org/install.html

Install MySql
* https://dev.mysql.com/downloads/mysql

## Running App

After downloading and installing, you'll finally be ready to import the code into an IDE of your choice and run App.java to launch the app.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

 * mvn test

### To initialize the database

The application runs on the on-board Tomcat server, on port 8080. The application works with a MySQL database.

Two possibilities to install the database
 * you can run the project's SQL script: src / main / resources / Data.sql
 * Or Go to the application.properties folder on line 11 (Warning: the folder which is in main ^^). 
   Remove the "#" to make this line operational. This will allow the database to be initialized.
   In order to persist the data it will make you enter line 11 again with the "#".


## Modelization
###  Physical data model
![MPD](MPD_PayMyBuddy.jpg)

###  Class Diagram
![Diagram](Diagram_PayMyBuddy.jpg)

