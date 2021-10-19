# Description
This is an example of a Spring Boot web app which provides CRUD operations on an Employee entity.
Created with [Spring Initializr](https://start.spring.io/).

Technologies demonstrated:

* Spring Boot
* Spring MVC
* Thymeleaf
* Hibernate, JPA
* MySQL

# Prerequisites
* Java 11
* Docker or a MySQL server

# Initial Setup
Run MySQL via Docker:
```
docker run -dp 3306:3306 \
     --name employees-mysql \
     -v ~/.docker-volumes/mysql-employees:/var/lib/mysql \
     -e MYSQL_ROOT_PASSWORD=password \
     mysql:5.7
```

Open a MySQL client and create the database for the app:
```
mysql> create database db_example;
mysql> create user 'springuser'@'%' identified by 'ThePassword';
mysql> grant all on db_example.* to 'springuser'@'%';
```

# Running the App
1. Open a terminal, make sure Java 11 is in the execution path and run:
    ```
    ./gradlew bootRun
    ```
    Or, launch the "Application" run configuration in IntelliJ (it should get created automatically).

2. Open a browser to http://localhost:8080/.
