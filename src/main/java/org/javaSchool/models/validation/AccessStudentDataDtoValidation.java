package org.javaSchool.models.validation;

import org.javaSchool.configs.reader.ConfigRegistry;
import org.javaSchool.configs.reader.queriesConfigReader.QueryDefinition;
import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.models.DAO.studentDaoUtils.StudentDaoUtils;

import java.util.List;

public class AccessStudentDataDtoValidation {

    public static void validateAccessStudentDataDto(AccessStudentDataDTO accessStudentDataDto)
    throws Exception{
        StringBuilder output = new StringBuilder();

        if(accessStudentDataDto.getStudentManagementAction().equals(AccessStudentData.StudentManagementAction.INVALID_ACTION)){
            output.append("StudentManagementAction is invalid");
            throw new Exception(output.toString());
        }

        try{
            validateAccessStudentDataQuery(accessStudentDataDto);
        } catch (Exception e) {
            output.append(e.getMessage());
        }

        if(!output.isEmpty()) throw new Exception(output.toString());
    }

    private static void validateAccessStudentDataQuery(AccessStudentDataDTO accessStudentDataDto)
    throws Exception{
        StringBuilder output = new StringBuilder();

        AccessStudentData.StudentManagementAction studentManagementAction = accessStudentDataDto.getStudentManagementAction();
        String type = "Queries_Config";
        String subtype = "Student_Dao";
        String configName = StudentDaoUtils.castActionToConfigName(studentManagementAction);
        QueryDefinition queryDefinition = (QueryDefinition) ConfigRegistry.getConfigFromRegistry(type, subtype, configName);

        List<String> requiredParams = queryDefinition.getRequiredParams();
        for(String param : requiredParams){
            if(param.equals("id")) if(accessStudentDataDto.getId() == null) output.append("id required");
            if(param.equals("name"))  if(accessStudentDataDto.getName() == null) output.append("name required");
        }

        if(!output.isEmpty()) throw new Exception(output.toString());
    }
}
