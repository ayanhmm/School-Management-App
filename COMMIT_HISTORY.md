### Created basic Structure
- Created base structure of the App utilizing Maven Build System
- Added readme and commit_history markdown websites
- created dirgit ectories using reverse domain name notation

### Created Basic Classes
- Created student class to represent one student based on id, name and other details
  - Override of the toString() method for printing the objects of this class properly
- Created AccessStudentData class to access and manipulate student data
  - Created enum studentManagementActions defining possible actions for AccessStudentData class Currently consists of
    - ADD_STUDENT
    - DELETE_STUDENT
    - DISPLAY_STUDENT
    - INVALID_ACTION
  - Defined main method in AccessStudentData to access items the class directly via terminal
  - Created accessStudentData method to communicate with the database in the future  
- created package named databaseConnectivity to support connections to various database, replacing the original idea of a single class with the same name

### Locally installed mysql server
- Setup the container
  - docker run -d \ --name mysql-student \ -e MYSQL_ROOT_PASSWORD=root \ -e MYSQL_DATABASE=student_manage \ -p 3306:3306 \ mysql:8.3
  - docker run -d --name mysql-student -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=student_manage -p 3306:3306 mysql:8.3
  - docker start mysql-student
- docker exec -it mysql-student mysql -u root -p
- SHOW DATABASES
- USE student_manage
- create table students(id int PRIMARY KEY, name VARCHAR(100));

### Added MySQL connectivity via JDBC
- completed implementation of method accessStudentData in class AccessStudentData
- created studentDao class to manipulate student data in mysql database.
- DAO stands for Data Access Object, a design pattern used to separate database operations from business logic  
- added mysql connector jar maven dependency and created ConnectionProvider for mysql

### Logs for proper debugging
- Added MAVEN dependencies for slf4j API for logging
  - This was chosen because it acts as an abstraction layer, it makes the application logging dependant on an interface rather than a specific logging engine
  - hence, it is flexible - allows switching logging implementation without changing the production code
  - Also has a clean API, makes it simple and readable
- slf4j is just an interface (Logger, LoggerFactory) - it requires an actual logging engine 
- Used Logback for that
- Added Logging wherever deemed necessary
- Switched to newer Java 21 

### Added Utils
- Added Utils package to provide common utilities needed by other packages
- Created JsonOutputFields to standardize the names of various fields present in the output fields of various json/map outputs across classes  
- Created Lookup maps for all enums to improve assignment logic 

### Unit Testing
- imported MAVEN dependency for JUnit for testing purposes
- JUnit is an opensource testing framework for Java used for unit testing
- inside src >test >java created studentManagementTests package to add unit tests for the same
- Added tests for accessStudentData function.

### Added Rest API Accessibility
- imported MAVEN dependency for Spring for the same
- Spring gives Built-in Web Server
- created SchoolAppRestAPI as a root api endpoint for the app and class StudentApp to host the spring boot application
- Springboot already contains Logback -> removed dependency from pom file
- Tested this app via postman
  - http: //localhost:8080 /javaschool/health