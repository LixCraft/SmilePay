
# Environnement Setting
> Tomcat 9.0.45.0
> 
> Java Compiler 1.8
> 
> JDK 1.8.0_281 (Oracle)
> 
> Development in IntelliJ IDEA Community 2021.1 (Build 211.6693.111)
> 

> Webservice API SOAP
> 
> Junit 4.11
> 
> Hibernate 4.3.6.Final
> 
> Database PostgreSql v13.2-2
> 
> PGAdmin 5.0

# Database Configuration
> Database parameters : src/main/resources/hibernate.cfg.xml

# WebService SOAP
> WebService Endpoint Configuration : 
> 
> - src/main/webapp/WEB-INF/sun-jaxws.xml
> 
> - src/main/webapp/WEB-INF/web.xml
> 
> Local WebService Endpoint URL  : 
> 
> - http://localhost:8080/SmilePay/ws/product

## Instructions

### MVP - Minimum Valuable Product

#### User Story 1
> As a bank, deposit money from a customer to his account, is allowed when superior to €0.01

#### User Story 2
> As a bank, withdraw money from a customer account, is allowed when no overdraft used

#### User Story 3
> As a bank, a customer can display its account balance

#### User Story 4
> As a bank, a customer can display its account transactions history
