package org.javaSchool.models.MS;

import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.models.validation.AccessStudentDataDtoValidation;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.utils.JsonInputFields;
import org.javaSchool.utils.JsonOutputFields;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccessStudentDataService {

    public static Map<String, Object> handle(AccessStudentDataDTO accessStudentDataDTO){
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

    private static Map<String, Object> dtoToMap(AccessStudentDataDTO accessStudentDataDTO){
        Map<String, Object> params = new HashMap<>();
        if(accessStudentDataDTO.getStudentManagementAction() != null)params.put(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue(), accessStudentDataDTO.getStudentManagementAction().toString());
        if(accessStudentDataDTO.getId() != null)params.put(JsonInputFields.ENTITY_ID.getValue(), accessStudentDataDTO.getId());
        if(accessStudentDataDTO.getName() != null)params.put(JsonInputFields.ENTITY_NAME.getValue(), accessStudentDataDTO.getName());
        return params;
    }
}
