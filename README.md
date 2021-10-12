# Description
This is an example of a Spring Boot web app which provides CRUD operations on an Employee entity.
Created with [Spring Initializr](https://start.spring.io/).

Technologies demonstrated:

* Spring MVC
* Thymeleaf
* Hibernate, JPA
* MySQL
* Skeleton CSS

# Initial Setup
Run MySQL via Docker:
```
docker run -dp 3306:3306 \
     --name spring-boot-mysql \
     -v /Users/cbiegay/temp/msql-data:/var/lib/mysql \
     -e MYSQL_ROOT_PASSWORD=password \
     mysql:5.7
```

Create the database for the app:
```
mysql> create database db_example;
mysql> create user 'springuser'@'%' identified by 'ThePassword';
mysql> grant all on db_example.* to 'springuser'@'%';
```

# Running the App
Open a terminal, make sure Java 11 is in the execution path and run:
```
./gradlew bootRun
```
Or, launch the "Application" run configuration in IntelliJ.

# Usage
Open a browser to http://localhost:8080/.
