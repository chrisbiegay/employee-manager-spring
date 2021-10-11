
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
Add an employee to the database:
```
curl localhost:8080/employee/add -d name='Mike Jones' -d department=Engineering
```

Fetch all employees:
```
curl http://localhost:8080/employee/list
```

Fetch all employees in a given department:
```
curl http://localhost:8080/employee/list?department=Accounting
```