#### Created basic Structure
- Created base structure of the App utilizing Maven Build System
- Added readme and commit_history markdown websites
- created dirgit ectories using reverse domain name notation

#### Created Basic Classes
- Created student class to represent one student based on id, name and other details
  - Override of the toString() method for printing the objects of this class properly
- Created AccessStudentData class to access and manipulate student data
  - Created enum studentManagementActions defining possible actions for AccessStudentData class Currently consists of
    - ADD_STUDENT
    - DELETE_STUDENT
    - DISPLAY_STUDENT
    - INVALID_ACTION
  - Defined main method in AccessStudentData to access items the class direclty via terminal
  - Created accessStudentData method to communicate with the database in the future  
- created package named databaseConnectivity to support connections to various database, replacing the original idea of a single class with the same name