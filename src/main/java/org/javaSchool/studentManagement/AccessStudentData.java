package org.javaSchool.studentManagement;

import org.javaSchool.utils.JsonInputFields;
import org.javaSchool.utils.JsonOutputFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
class to allow management of student data
 */
public class AccessStudentData {

    /*
    Defines all possible operations this class caters to
     */
    public enum StudentManagementAction {
        ADD_STUDENT("ADD_STUDENT"),
        DELETE_STUDENT("DELETE_STUDENT"),
        DISPLAY_STUDENT("DISPLAY_STUDENT"),
        DISPLAY_ALL_STUDENTS("DISPLAY_ALL_STUDENTS"),
        UPDATE_STUDENT("UPDATE_STUDENT"),
        INVALID_ACTION("INVALID_ACTION");

        private final String value;
        private static final Map<String, StudentManagementAction> LOOKUP = new HashMap<>();
        static {
            for (StudentManagementAction action : values()) {
                LOOKUP.put(action.value, action);
            }
        }

        StudentManagementAction(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }

        public static StudentManagementAction fromString(String value){
            return LOOKUP.getOrDefault(value, INVALID_ACTION);
        }

    }

    /*
    Method to access items the class directly via terminal
     */
    public static void main(String[] args){

        while(true){
            StudentManagementAction studentManagementAction = getUserInput();
            if(studentManagementAction.equals(StudentManagementAction.INVALID_ACTION)) return;

            Map<String, Object> params = new HashMap<>();
            params.put(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue(), studentManagementAction.toString());
            params.put(JsonInputFields.ENTITY_ID.getValue(), 2);
            params.put(JsonInputFields.ENTITY_NAME.getValue(), "Ayan");

            Map<String, Object> output = new HashMap<>();
            if(!params.containsKey(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue())){
                output.put(JsonOutputFields.ERROR_MESSAGE.getValue(), "Action field is not present in params");
                System.out.println(output);
                continue;
            }
            StudentManagementAction action = StudentManagementAction.fromString((String) params.get(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue()));
            if(StudentManagementAction.INVALID_ACTION.equals(action)){
                output.put(JsonOutputFields.ERROR_MESSAGE.getValue(), "Specified action is Invalid");
                System.out.println(output);
                continue;
            }

            output = accessStudentData(params);
            System.out.println(output);
        }

    }

    private static StudentManagementAction getUserInput(){
        Integer input = 4;
        System.out.println("press 1 to ADD_STUDENT");
        System.out.println("press 2 to DELETE_STUDENT");
        System.out.println("press 3 to DISPLAY_STUDENT");
        System.out.println("press 4 to DISPLAY_ALL_STUDENTS");
        System.out.println("press 5 to UPDATE_STUDENT");
        System.out.println("enter any other number to Exit");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            input = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException ex) {
            System.out.println("Exception occurred while taking input" + ex.getMessage());
        }

        StudentManagementAction action = switch (input){
            case 1 -> StudentManagementAction.ADD_STUDENT;
            case 2 -> StudentManagementAction.DELETE_STUDENT;
            case 3 -> StudentManagementAction.DISPLAY_STUDENT;
            case 4 -> StudentManagementAction.DISPLAY_ALL_STUDENTS;
            case 5 -> StudentManagementAction.UPDATE_STUDENT;
            default -> StudentManagementAction.INVALID_ACTION;
        };
        return action;
    }

    /*
    use this method directly if accessing via API calls
     */
    public static Map<String, Object> accessStudentData(Map<String, Object> params){
        Map<String, Object> output = new HashMap<>();
        output.put(JsonOutputFields.REQUEST_PARAMS.getValue(), params);

        StudentManagementAction action = StudentManagementAction.fromString((String) params.get(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue()));
        Student student = getStudentFromParams(params);
        output.putAll( StudentDao.execute(action, student));
        return output;
    }

    private static Student getStudentFromParams(Map<String, Object> params){
        Student student = new Student();
        if(params.containsKey(JsonInputFields.ENTITY_ID.getValue())) student.setId((Integer) params.get(JsonInputFields.ENTITY_ID.getValue()));
        if(params.containsKey(JsonInputFields.ENTITY_NAME.getValue())) student.setName((String) params.get(JsonInputFields.ENTITY_NAME.getValue()));
        return student;
    }
}
