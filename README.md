# Information
API - isMutant

## Technologies
	1. Maven - Dependency management
	2. JPA (Hibernate) - Persistance layer
	3. Spring - Dependency injection, etc.
	4. Spring Boot - Spring bootstrap
	5. Tomcat - Servlet engine
	6. MySQL
	7. JUnit - Testing


# Development

## IDE
	Run MutantApplication.java

## Console
	mvn spring-boot:run


# Deployment - Local
Create the database in MySQL:

	mysql> CREATE DATABASE mutantDna;

If troubles with privileges or "access denied" run:

	mysql> GRANT ALL PRIVILEGES ON challenge.* TO 'root'@'localhost';

Configure the file 'application.properties' and run:

	mvn clean install

# API - Execution

##### Detect if a DNA is mutant:

	https://meli-mutant-api.herokuapp.com/mutant/ (include final slash '/')

##### Get statistics of DNA validation:

	https://meli-mutant-api.herokuapp.com/stats

# Testing

To execute the tests run:

	mvn clean test
