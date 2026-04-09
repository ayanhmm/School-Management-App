### Created basic Structure
- Created base structure of the App utilizing Maven Build System
- Added readme and commit_history markdown websites
- created directories using reverse domain name notation

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

### MySQL connectivity via JDBC
- completed implementation of method accessStudentData in class AccessStudentData
- created studentDao class to manipulate student data in mysql database.
- DAO stands for Data Access Object, a design pattern used to separate database operations from business logic  
- added mysql connector jar maven dependency and created ConnectionProvider for mysql
- can also use hibernate which is actually build over JDBC
- can also use JPA which also gives additional abilities

### Logs 
- Added MAVEN dependencies for slf4j API for logging
  - This was chosen because it acts as an abstraction layer, it makes the application logging dependant on an interface rather than a specific logging engine
  - hence, it is flexible - allows switching logging implementation without changing the production code
  - Also has a clean API, makes it simple and readable
- slf4j is just an interface (Logger, LoggerFactory) - it requires an actual logging engine 
- Used Logback for that
- Added Logging wherever deemed necessary
- Switched to newer Java 21 

### Utils
- Added Utils package to provide common utilities needed by other packages
- Created JsonOutputFields to standardize the names of various fields present in the output fields of various json/map outputs across classes  
- Created Lookup maps for all enums to improve assignment logic 

### Unit Testing
- imported MAVEN dependency for JUnit for testing purposes
- JUnit is an opensource testing framework for Java used for unit testing
- inside src >test >java created studentManagementTests package to add unit tests for the same
- Added tests for accessStudentData function.

### Rest API Accessibility
- imported MAVEN dependency for Spring for the same
- Spring gives Built-in Web Server
- created SchoolAppRestAPI as a root api endpoint for the app and class StudentApp to host the spring boot application
- Springboot already contains Logback -> removed dependency from pom file
- Tested this app via postman
  - http: //localhost:8080 /javaschool/health

### Student Management API
- Added StudentManagementRestAPI as a common API handler for all classes related to StudentManagement package
- Added @Operation annotation to api methods to explain their functionality 
- imported openAPI MAVEN dependency for the same
- Created JsonInputFields to standardize the names of various fields present in the input fields of various json/map inputs across classes  

### Data Transfer Objects
- Created package models.DTO to store all the DTO classes
- created a dto to take request input in the accessStudentDataAPI class
- used @JsonProperty to define what input gets mapped where
  - @JsonProperty does not work if parameter is dynamic which happens when we put JsonInputFields.ENTITY_ID.getValue() as parameter
  - Hence updated jsonInputFields to have Strings declared as constants fetched from JsonFieldsTextValues class - similarly updated jsonOutputFields for consistency
  - then those constants are used in constructors of JsonInputFields as well as input for @JsonProperty
- Instead of taking input as a map, the accessStudentDataAPI now utilizes the newly created dto 
  - the previous method marked as @Depricated
  - Still getting ambiguous mapping error, introduces versioning in API endpoint

### Additional APIs and Queries
- introduces new API endpoint 
  - /possibleInputFields so that frontend knows what possible fields they can share 
  - /accessData/possibleActions to List Possible actions for accessStudentDataAPI Json input.
- Added Display all students query to AccessStudentData.
- Added Update student query to AccessStudentData.
- Extracted Query Logic from StudentDao to its utils to prevent cluttering of the class

### Query Configs
- created directory resources.configs to store all config files
- created package configs to store all config extraction logic
  - configs.reader package to abstract logic to read all configs
  - created configReader class to run and extract all configs at runtime itself
  - Created queriesConfigReader package to store classes with logic to read the json files for type query config
    - Added @JsonIgnoreProperties(ignoreUnknown = true) to avoid error due to extra fields
    - updated build section of pom.xml so that it can now read json files
  - Created QueryRegistry to store extracted queries
- Extracted Query logic outside StudentDao to get from configs

### Config registry
- configRegistery class to store all the read configs
- RegistryKey to act as key to store configs in registry
  - hashCode() override used inside for utilization in Hash-based Collections, such as HashMap, HashSet, or Hashtable

### Config integration
- updated GetQueryForPreparedStatement in StudentDao to use configRegistry.
- Extracted SetParameters to preparedStatement logic outside StudentDao to get from configs
- Marked ConfigRegistry class as @Component which tells spring this class has a method that needs to run at startup
  - Marked the above method named init() using @PostConstruct
  - Spring was not detecting the declared @component
    - because it only checks classes inside the package the studentApp is present
    - hence, moved it outside
- Addressed compiler warnings like 
  - If you are using @PostMapping, you do not need to @RequestMapping -> fixed that
  - removed unused imports, spellings
  - improved assertions in tests
  - made variables final wherever needed

### Validation Layer
- QueriesConfig now also specifies the datatypes of params.
- Validation package in models to house all validation procedures
- AccessStudentDataDtoValidation to validate db query requests - moved validation procedure outside of accessStudentData
- optimised setParameters for preparedStatement to take datatype from config

### Service based architecture
- extracted service logic from api to separate service entity
- created directory models.ms for the same
  - @Service: This tells Spring that this class is a "Bean." Spring will automatically create an instance of it and manage its lifecycle.
  - @Autowired: This is Dependency Injection. Instead of you writing new Registry(), Spring "plugs in" the already existing instances of the Registry into this class.
    - Later can replace it with custom annotation and logic
- relocated DAO to models for better structuring purposes

- ms for apis
