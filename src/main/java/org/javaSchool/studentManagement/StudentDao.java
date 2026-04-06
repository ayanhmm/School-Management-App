package org.javaSchool.studentManagement;

import org.javaSchool.databaseConnectivity.mysql.ConnectionProvider;
import org.javaSchool.utils.JsonFieldsTextValues;
import org.javaSchool.utils.JsonOutputFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDao.class);

    private static final Set<AccessStudentData.StudentManagementAction> UPDATE_ACTIONS = Set.of(
            AccessStudentData.StudentManagementAction.ADD_STUDENT,
            AccessStudentData.StudentManagementAction.DELETE_STUDENT
    );

    private static final Set<AccessStudentData.StudentManagementAction> QUERY_ACTIONS = Set.of(
            AccessStudentData.StudentManagementAction.DISPLAY_STUDENT
    );

    public static Map<String, Object> execute(AccessStudentData.StudentManagementAction action, Student student){
        Map<String, Object> output = new HashMap<>();

        if(UPDATE_ACTIONS.contains(action)) output.putAll(executeUpdateAction(action, student));
        else if(QUERY_ACTIONS.contains(action)) output.putAll(executeQueryAction(action, student));
        else{
            output.put(JsonOutputFields.ERROR_MESSAGE.getValue(), "StudentDao.execute() does not have implementation type defined for the action");
            output.put(JsonOutputFields.SUCCESS_BOOLEAN.getValue(), false);
        }
        return output;
    }
    private static Map<String, Object> executeUpdateAction(AccessStudentData.StudentManagementAction action, Student student){
        boolean successBoolean = false;
        Map<String, Object> output = new HashMap<>();

        try{
            Connection connection = ConnectionProvider.createConnection();
            PreparedStatement preparedStatement = getPreparedStatement(action, student, connection);
            preparedStatement.executeUpdate();
            successBoolean = true;
        }
        catch (Exception ex){
            LOGGER.warn("Unable to perform update action to MySQL Database", ex);
            output.put(JsonOutputFields.ERROR_MESSAGE.getValue(), "Unable to perform update action to MySQL Database: {}");
        }

        output.put(JsonOutputFields.SUCCESS_BOOLEAN.getValue(), successBoolean);
        return output;
    }
    private static Map<String, Object> executeQueryAction(AccessStudentData.StudentManagementAction action, Student student){
        boolean successBoolean = false;
        Map<String, Object> output = new HashMap<>();

        try{
            Connection connection = ConnectionProvider.createConnection();
            PreparedStatement preparedStatement = getPreparedStatement(action, student, connection);
            ResultSet resultSet = preparedStatement.executeQuery();

            Set<Map<String, String>> result = new HashSet<>();
            while(resultSet.next()){
                Map<String, String> row = new HashMap<>();
                row.put(JsonOutputFields.ENTITY_ID.getValue(), String.valueOf(resultSet.getInt("id")));
                row.put(JsonOutputFields.ENTITY_NAME.getValue(), resultSet.getString("name"));
                result.add(row);
            }
            output.put(JsonOutputFields.REQUEST_RESULT.getValue(), result);

            successBoolean = true;
        }
        catch (Exception ex){
            LOGGER.warn("Unable to perform query action to MySQL Database", ex);
            output.put(JsonOutputFields.ERROR_MESSAGE.getValue(), "Unable to perform query action to MySQL Database: {}");
        }

        output.put(JsonOutputFields.SUCCESS_BOOLEAN.getValue(), successBoolean);
        return output;
    }

    private static PreparedStatement getPreparedStatement(AccessStudentData.StudentManagementAction action, Student student, Connection connection)
        throws Exception{
        String query = preparedStatementQuery(action);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            setParameters(action, student, preparedStatement);
            return preparedStatement;
        } catch (Exception ex) {
            LOGGER.warn("Unable to getPreparedStatement for MySQL Database: {}", ex.getMessage());
            throw ex;
        }
    }
    private static String preparedStatementQuery(AccessStudentData.StudentManagementAction action){
        return switch (action){
            case ADD_STUDENT -> addStudentQuery();
            case DELETE_STUDENT -> deleteStudentQuery();
            case DISPLAY_STUDENT -> displayStudentQuery();
            default -> "";
        };
    }
    private static String addStudentQuery(){
        return "insert into students(id, name) values (?, ?)";
    }
    private static String deleteStudentQuery(){
        return "delete from students where id = ?";
    }
    private static String displayStudentQuery(){
        return "select * from students where id = ?";
    }
    private static void setParameters(AccessStudentData.StudentManagementAction action, Student student, PreparedStatement preparedStatement)
        throws Exception{
        try{
            preparedStatement.setInt(1, student.getId());
            if(action.equals(AccessStudentData.StudentManagementAction.ADD_STUDENT)) preparedStatement.setString(2, student.getName());
        } catch (Exception ex) {
            LOGGER.warn("Unable to setParameters in prepared statement for MySQL Database: {}", ex.getMessage());
            throw ex;
        }
    }


}
