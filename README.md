# Technology
Java 11  
Spring Boot  
MongoDB  

# Setup  
1. Please run command 'java -jar target/APP_NAME.jar'.  
2. Visit URL 'http://localhost:8080'.  

# Solution Overview  
-Store URL different parts such as path, query parameters as shown below figure.  

-If exact match isn't found, then search any match of path or query parameter.  
PATH1='product' OR tag='1234' OR tag='5678'  

-Count total match count of path and query parameters and pick up record having maximum count.  
