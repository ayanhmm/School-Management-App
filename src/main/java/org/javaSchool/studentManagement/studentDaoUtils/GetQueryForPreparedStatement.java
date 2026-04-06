package org.javaSchool.studentManagement.studentDaoUtils;

import org.javaSchool.studentManagement.AccessStudentData;

public class GetQueryForPreparedStatement {
    public static String ADD_STUDENT_QUERY = "insert into students(id, name) values (?, ?)";
    public static String DELETE_STUDENT_QUERY = "delete from students where id = ?";
    public static String DISPLAY_STUDENT_QUERY = "select * from students where id = ?";
    public static String DISPLAY_ALL_STUDENTS_QUERY = "select * from students";
    public static String UPDATE_STUDENT_QUERY = "update students set name = ? where id = ?";


    public static String getQueryForPreparedStatement(AccessStudentData.StudentManagementAction action){
        return switch (action){
            case ADD_STUDENT -> addStudentQuery();
            case DELETE_STUDENT -> deleteStudentQuery();
            case DISPLAY_STUDENT -> displayStudentQuery();
            case DISPLAY_ALL_STUDENTS -> displayAllStudentsQuery();
            case UPDATE_STUDENT -> updateStudentQuery();
            default -> "";
        };
    }
    private static String addStudentQuery(){
        return ADD_STUDENT_QUERY;
    }
    private static String deleteStudentQuery(){
        return DELETE_STUDENT_QUERY;
    }
    private static String displayStudentQuery(){
        return DISPLAY_STUDENT_QUERY;
    }
    private static String displayAllStudentsQuery(){
        return DISPLAY_ALL_STUDENTS_QUERY;
    }
    private static String updateStudentQuery(){
        return UPDATE_STUDENT_QUERY;
    }
}
