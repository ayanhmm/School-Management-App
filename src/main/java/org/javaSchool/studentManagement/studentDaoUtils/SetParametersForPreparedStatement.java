package org.javaSchool.studentManagement.studentDaoUtils;

import org.javaSchool.configs.reader.ConfigRegistry;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.studentManagement.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.List;

public class SetParametersForPreparedStatement {
    private static final Logger LOGGER = LoggerFactory.getLogger(SetParametersForPreparedStatement.class);

    private static final String TYPE = "Queries_Config";
    private static final String SUB_TYPE = "Student_Dao";

    public static void setParameters(AccessStudentData.StudentManagementAction action, Student student, PreparedStatement preparedStatement)
            throws Exception{

        String configName = StudentDaoUtils.castActionToConfigName(action);
        QueryDefinition queryDefinition = (QueryDefinition) ConfigRegistry.getConfigFromRegistry(TYPE, SUB_TYPE, configName);
        List<String> params = queryDefinition.getParams();

        for( int i = 0; i<params.size(); ++i){
            String param = params.get(i);
            setParameter(i+1, param, student, preparedStatement);
        }

    }

    private static void setParameter(Integer index, String parameter, Student student, PreparedStatement preparedStatement)
        throws Exception{
        try{
            if(parameter.equals("id")) preparedStatement.setInt(index, student.getId());
            if(parameter.equals("name")) preparedStatement.setString(index, student.getName());
        }
        catch (Exception ex){
            LOGGER.warn("failed to setParameter {} into {}", parameter, preparedStatement);
            throw ex;
        }
    }
}
