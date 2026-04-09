package org.javaSchool.models.DAO.studentDaoUtils;

import org.javaSchool.configs.reader.ConfigRegistry;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import org.javaSchool.studentManagement.AccessStudentData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetQueryForPreparedStatement {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetQueryForPreparedStatement.class);

    @Deprecated
    private static final String ADD_STUDENT_QUERY = "insert into students(id, name) values (?, ?)";
    @Deprecated
    private static final String DELETE_STUDENT_QUERY = "delete from students where id = ?";
    @Deprecated
    private static final String DISPLAY_STUDENT_QUERY = "select * from students where id = ?";
    @Deprecated
    private static final String DISPLAY_ALL_STUDENTS_QUERY = "select * from students";
    @Deprecated
    private static final String UPDATE_STUDENT_QUERY = "update students set name = ? where id = ?";

    private static final String TYPE = "Queries_Config";
    private static final String SUB_TYPE = "Student_Dao";


    public static String getQueryForPreparedStatement(AccessStudentData.StudentManagementAction action){
        String configName = StudentDaoUtils.castActionToConfigName(action);
        QueryDefinition queryDefinition = (QueryDefinition) ConfigRegistry.getConfigFromRegistry(TYPE, SUB_TYPE, configName);

        if(queryDefinition == null){
            LOGGER.warn("Unable to fetch queryDefinition from config registry");
            return "";
        }
        if(queryDefinition.getQuery() == null){
            LOGGER.warn("Unable to fetch queryDefinition from config registry");
            return "";
        }

        return queryDefinition.getQuery();
    }

    @Deprecated
    private static String addStudentQuery(){
        return ADD_STUDENT_QUERY;
    }
    @Deprecated
    private static String deleteStudentQuery(){
        return DELETE_STUDENT_QUERY;
    }
    @Deprecated
    private static String displayStudentQuery(){
        return DISPLAY_STUDENT_QUERY;
    }
    @Deprecated
    private static String displayAllStudentsQuery(){
        return DISPLAY_ALL_STUDENTS_QUERY;
    }
    @Deprecated
    private static String updateStudentQuery(){
        return UPDATE_STUDENT_QUERY;
    }
}
