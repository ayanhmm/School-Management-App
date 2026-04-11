package org.javaSchool.models.DAO.studentDaoUtils;

import org.javaSchool.studentManagement.AccessStudentData;

public class StudentDaoUtils {
    public static final String NAME_ADD_STUDENT_QUERY = "add_student_query";
    public static final String NAME_DELETE_STUDENT_QUERY = "delete_student_query";
    public static final String NAME_DISPLAY_STUDENT_QUERY = "display_student_query";
    public static final String NAME_DISPLAY_ALL_STUDENTS_QUERY = "display_all_students_query";
    public static final String NAME_UPDATE_STUDENT_QUERY = "update_student_query";

    public static String castActionToConfigName(AccessStudentData.StudentManagementAction action){
        return switch (action){
            case ADD_STUDENT -> NAME_ADD_STUDENT_QUERY;
            case DELETE_STUDENT -> NAME_DELETE_STUDENT_QUERY;
            case DISPLAY_STUDENT -> NAME_DISPLAY_STUDENT_QUERY;
            case DISPLAY_ALL_STUDENTS -> NAME_DISPLAY_ALL_STUDENTS_QUERY;
            case UPDATE_STUDENT -> NAME_UPDATE_STUDENT_QUERY;
            default -> "";
        };
    }
}
