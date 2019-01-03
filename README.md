# EmployeeManagementService

## Summary
This is a simple demonstration of a spring boot CRUD app for managing basic employee information. It is in no way intended to be used as a production applications as many shortcuts have been taken. It is simply a demonstration of some simple spring REST technologies and patterns.

### Pre-loaded data

To simplify the usage of this API I have added a data.sql file to pre-load 6 employees to the embedded database. 

**data.sql**

```sql
INSERT INTO employees (FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS) VALUES ('Ryan','Brown', 'K', '1972-09-05', '2016-12-05', 'ACTIVE');
INSERT INTO employees (FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS) VALUES ('Isaac','Lavoie', 'A', '1982-12-12', '2016-12-05', 'ACTIVE');
INSERT INTO employees (FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS) VALUES ('Joe','Shmoe', 'B', '1965-02-22', '2016-12-05', 'ACTIVE');
INSERT INTO employees (FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS) VALUES ('Andrea','Fugate', 'R', '1976-07-17', '2016-12-05', 'ACTIVE');
INSERT INTO employees (FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS) VALUES ('Sean','Royal', 'E', '1969-08-31', '2016-12-05', 'ACTIVE');
INSERT INTO employees (FIRST_NAME, LAST_NAME, MIDDLE_INITIAL, DATE_OF_BIRTH, DATE_OF_EMPLOYMENT, STATUS) VALUES ('Johnny','Nogood', 'B', '1969-08-31', '2016-12-05', 'ACTIVE');
```

## Swagger (OpenAPI) documentation

A rudimentary set of OpenAPI documentation has been included for this API. 

    Swagger UI: http://localhost:8080/swagger-ui.html
    API docs: http://localhost:8080/v2/api-docs


## Build

To build the project you will be required to have the latest version of gradle installed. Then you will just run the following command to generate the spring boot jar:

```bash
$ gradle build
```
This will run the tests and output the single jar required to run a spring boot application.

## Docker

This project has been designed to run in docker for package portability. While this project could be run using either the java exec or gradle run commands, I have optimized this project for running in docker.

### Packaging

To package the app in a docker container and publish to your local docker repo run the following command:

```bash
$ docker build  -t employeeapi .
```
### Running
Assuming that the packaging completes as expected you should be able to run the container with the following command:

```bash
docker run -d -p 8080:8080 employeeapi .
```
## Testing the Employee API

Testing can be done with the included postman collection (Employee API.postman_collection.json)

the actions in the postman collection are as follows:

1. Create a new api user that can be used to authenticate future requests. This is just kept using an in-memory idenity provider for simplification.
2. Authenticate with the new user and save the Authorization token as a global variable for all authenticated requests.
3. Get all pre-loaded employees and save the id of the first to a global variable.
4. Get single user by id. (Using the id retrieved above)
5. Create a new employee and save the returned location header to a global variable.
6. Get the new employee by id.
7. Update the new employee with a new birthdate (to correct the initial one) and appending "--UPDATED" to the end of the last name.
8. Delete the new employee by using the location variable saved globally. (This actually marks the employee as "INACTIVE" in the db thus making them effectively deleted)

## TODOs

- Unit test, unit test, unit test!!
- Code documentation
- Improved swagger doumentation
- Add testing instructions that do not rely upon postman collection.

