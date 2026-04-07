package org.javaSchool.studentManagement.studentDaoUtils;

import org.javaSchool.studentManagement.AccessStudentData;

public class StudentDaoUtils {

    public static String castActionToConfigName(AccessStudentData.StudentManagementAction action){
        return switch (action){
            case ADD_STUDENT ->"add_student_query";
            case DELETE_STUDENT -> "delete_student_query";
            case DISPLAY_STUDENT -> "display_student_query";
            case DISPLAY_ALL_STUDENTS -> "display_all_students_query";
            case UPDATE_STUDENT -> "update_student_query";
            default -> "";
        };
    }
}
