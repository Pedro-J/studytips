
#Description
- Studytips is an application......

#Tecnologies and Frameworks
- Spring Boot
- Spring Data
- Spring Security
- Spring MVC
- Angular 2.2.0
- JAVA 8

#Features
- Token based authentication, Json Web Token, HMAC implementation and 
HMAC Filter with Spring Security by Michael DESIGAUD (https://github.com/RedFroggy/angular-spring-hmac/tree/angular2)
- Users views, business and model;
- Areas views, business and model;
- Tips views, business and model;
- WebTips views, business and model;
- UserTips views, business and model;

#Installation

1 - Update your maven dependecies;

2 - See the file "application.properties" in "src/main/resources" and setup your databases properties, 
it must be created a schema with the same name as the one in "spring.datasource.url".

3 - To create the tables and colunms set the "spring.jpa.hibernate.ddl-auto" from update to create, and then 
start the application through the file "StudyTipsApplication" in the package "com.studytips". 
The hibernate must setup your database correctly, if the application starts without errors. 
To finish this part return "spring.jpa.hibernate.ddl-auto" to update.

4 - Execute the file "src/main/resources/import_start.sql" in your SGBD;

5 - Run the application as specified in the "item 3", then go to http://localhost:8080;

6 - Login options:
	
	* You can login as admin through login:admin and password:admin
	* You can login as manager through login:manager and password:manager
	* You can login as user through login:user and password:user
	

#To run Java unit tests
````bash
$ mvn test
````

#To run the application
````bash
$ mvn spring-boot:run
````

