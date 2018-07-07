## Neotech
Implementation of a test task for Neotech  

## Requirements
- Java 8  
- Maven 3  

## How to configure
This solutions works with MySQL database. All configurations are located in '/neotech/NeotechTestTask/db.properties' file.
To change db name / user / password just edit this file and build the project

## How to build solution
Go to 'neotech/NeotechTestTask/' and  run maven command 'mvn clean install'.   
JUnit tests will be run (unit tests and functional test ) and a 'neotech/NeotechTestTask/target' folder will be created.  
The target folder contains two jars: Neotech-0.0.1.jar (without libraries) and Neotech-0.0.1-jar-with-dependencies.jar 
(with all required dependencies)

## How to run solution 
You can do with command line  
- build solution  
- run application, you have two options:  
- - without 'p' parameter: java -jar target\Neotech-0.0.1-jar-with-dependencies.jar  
- - with 'p' parameter: java -jar target\Neotech-0.0.1-jar-with-dependencies.jar -p  
