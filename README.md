
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

#### 1.Install Tomcat
> Install Tomcat 9.0.45.0 on your computer :
> 
> - Home Page : https://tomcat.apache.org/download-90.cgi
> - Download link : https://downloads.apache.org/tomcat/tomcat-9/v9.0.45/bin/apache-tomcat-9.0.45.zip

#### 2.Install PostgreSql/PgAdmin
> Install Postrges 13.2 on your computer :
> - Home Page : https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

#### 3.Create Database
> Open your PgAdmin
> - Create a database and report your configuration database in the project file "src/main/resources/hibernate.cfg.xml" 
> - Initialize database structure by excuting the sql script : src/main/database/creation/creationBDD.sql
> - You can initialize the database with samples by executing the sql script : src/main/database/creation/InitBDD.sql

#### 4.Import project
> - Import project to your IDE 
> - Configure Tomcat Deployement (Context, Port)
![image](https://user-images.githubusercontent.com/41443590/115268189-4c95e880-a13a-11eb-97fd-4f4f845b089a.png)
> - Configure your project with JDK 1.8.0_281 (Oracle)

#### 5.Install
> - Run maven install to test the project and execute Junit tests

#### 6.Deployment
> - Deploy your war package on Tomcat
> 
#### 7.Deployment
> - Webservice SOAP description is exposed on : http://localhost:8080/SmilePay/ws/product
