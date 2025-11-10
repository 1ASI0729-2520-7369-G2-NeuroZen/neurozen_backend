# Neurozen Platform

## Summary
Neurozen Platform - Psychological Appointments Management System, illustrating development with Java, Spring Boot Framework, and Spring Data JPA on MySQL Database. It also illustrates open-api documentation configuration and integration with Swagger UI.

## Features
- RESTful API
- OpenAPI Documentation
- Swagger UI
- Spring Boot Framework
- Spring Data JPA
- Validation
- MySQL Database
- Domain-Driven Design
- Clean Architecture

## Bounded Contexts
This version of Neurozen Platform is divided into three bounded contexts: Appointments, Assessments, and Reports.

### Appointments Context

The Appointments Context is responsible for managing psychological appointments between employees and psychologists. It includes the following features:

- Create a new appointment.
- Get an appointment by id.
- Get all appointments.
- Get appointments by employee id.
- Get appointments by psychologist id.
- Confirm an appointment.
- Start an appointment.
- Complete an appointment.
- Cancel an appointment.
- Reschedule an appointment.

### Assessments Context

The Assessments Context is responsible for managing emotional and psychological assessments for employees. Its features include:

- Create an Assessment.
- Get an Assessment by id.
- Update an Assessment information.
- Get all Assessments.
- Get all Assessments by employee id.

Assessment types include: Emotional State, Stress Level, Anxiety, Depression, and General Wellbeing.

### Reports Context

The Reports Context is responsible for generating reports about employee progress, emotional state, appointments, and assessments. Its features include:

- Generate a new report.
- Get a report by id.
- Get all reports.
- Get all reports by employee id.

Report types include: Employee Progress, Emotional State Summary, Appointment Summary, and Assessment Summary.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.5.7/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.5.7/reference/htmlsingle/index.html#using.devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/3.5.7/reference/htmlsingle/index.html#io.validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.5.7/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
