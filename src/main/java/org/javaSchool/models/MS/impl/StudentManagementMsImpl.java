package org.javaSchool.models.MS.impl;

import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.models.MS.StudentManagementMs;
import org.javaSchool.models.MS.utils.MsImplMetadata;
import org.javaSchool.models.validation.AccessStudentDataDtoValidation;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.utils.jsonIO.JsonInputFields;
import org.javaSchool.utils.jsonIO.JsonOutputFields;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@MsImplMetadata(StudentManagementMs.class)
public class StudentManagementMsImpl implements StudentManagementMs {

    public Map<String, Object> handleAccessStudentDataAPI(AccessStudentDataDTO accessStudentDataDTO){
        Map<String, Object> output = new HashMap<>();

        try{
            AccessStudentDataDtoValidation.validateAccessStudentDataDto(accessStudentDataDTO);
        } catch (Exception e) {
            output.put(JsonOutputFields.ERROR_MESSAGE.getValue(), e.getMessage());
            output.put(JsonOutputFields.SUCCESS_BOOLEAN.getValue(), false);
            return output;
        }

        Map<String, Object> params = dtoToMap(accessStudentDataDTO);
        return AccessStudentData.accessStudentData(params);
    }

    public Set<String> handleListPossibleActions(){
        Set<String> output = new HashSet<>();
        for(AccessStudentData.StudentManagementAction field : AccessStudentData.StudentManagementAction.values()){
            output.add(field.getValue()) ;
        }
        return output;
    }

    private static Map<String, Object> dtoToMap(AccessStudentDataDTO accessStudentDataDTO){
        Map<String, Object> params = new HashMap<>();
        if(accessStudentDataDTO.getStudentManagementAction() != null)params.put(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue(), accessStudentDataDTO.getStudentManagementAction().toString());
        if(accessStudentDataDTO.getId() != null)params.put(JsonInputFields.ENTITY_ID.getValue(), accessStudentDataDTO.getId());
        if(accessStudentDataDTO.getName() != null)params.put(JsonInputFields.ENTITY_NAME.getValue(), accessStudentDataDTO.getName());
        return params;
    }
}
