package org.javaSchool.studentManagement.studentDaoUtils;

import org.javaSchool.configs.reader.ConfigRegistry;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.studentManagement.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class SetParametersForPreparedStatement {
    private static final Logger LOGGER = LoggerFactory.getLogger(SetParametersForPreparedStatement.class);

    private static final String TYPE = "Queries_Config";
    private static final String SUB_TYPE = "Student_Dao";

    public static void setParameters(AccessStudentData.StudentManagementAction action, Student student, PreparedStatement preparedStatement)
            throws Exception{

        String configName = StudentDaoUtils.castActionToConfigName(action);
        QueryDefinition queryDefinition = (QueryDefinition) ConfigRegistry.getConfigFromRegistry(TYPE, SUB_TYPE, configName);
        List<String> requiredParams = queryDefinition.getRequiredParams();
        Map<String, String> paramDatatype = queryDefinition.getParamDatatype();

        for( int i = 0; i<requiredParams.size(); ++i){
            String param = requiredParams.get(i);
            String datatype = paramDatatype.get(param);
            setParameter(i+1, param, student, preparedStatement, datatype);
        }

    }

    private static void setParameter(Integer index, String parameter, Student student, PreparedStatement preparedStatement, String datatype)
        throws Exception{
        try{
            if(datatype.equals("Integer")){
                Integer value = 0;
                if(parameter.equals("id")) value = student.getId();
                preparedStatement.setInt(index, value);
            }
            if(datatype.equals("String")){
                String value = "";
                if(parameter.equals("name")) value = student.getName();
                preparedStatement.setString(index,value);
            }
        }
        catch (Exception ex){
            LOGGER.warn("failed to setParameter {} into {}", parameter, preparedStatement);
            throw ex;
        }
    }
}
