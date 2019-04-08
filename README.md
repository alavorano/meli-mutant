# Information
API - isMutant

## Technologies
	1. Maven - Dependency management
	2. JPA (Hibernate) - Persistance layer
	3. Spring - Dependency injection, etc.
	4. Spring Boot - Spring bootstrap
	5. Tomcat - Servlet engine
	6. MySQL


# Development

## IDE
	Run MutantApplication.java

## Console
	mvn spring-boot:run


# Deployment
Create the database in MySQL:

	mysql> CREATE DATABASE mutantDna;

If troubles with privileges or "access denied" run:

	mysql> GRANT ALL PRIVILEGES ON challenge.* TO 'root'@'localhost';

Configure the file 'application.properties' and run:

	mvn clean install

# API - Execution (by command)

*(Using [Postman](https://www.getpostman.com/) the HTTP 200-OK and HTTP 403-Forbidden will become visible)*

##### Detect if a DNA is mutant:

	curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d 'DNA_TO_TEST' http://localhost:8080/mutant/ # DNA_TO_TEST->{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}

##### Get statistics of DNA validation:

	curl -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/stats

# Testing (To be implemented)

To execute the tests run:

	mvn clean test
