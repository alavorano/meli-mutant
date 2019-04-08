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

# Testing (To be implemented)

To execute the tests run:
	mvn clean test
