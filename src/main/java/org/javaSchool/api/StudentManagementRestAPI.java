package org.javaSchool.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.utils.JsonInputFields;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/javaschool/student")
public class StudentManagementRestAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementRestAPI.class);

    @PostMapping("/accessData/v1")
    @Operation(
            operationId = "Access Students Database",
            description = "Allows management of data for students"
    )
    @Deprecated(since = "new method introduced to handle accessStudentDataDTO")
    public Map<String, Object> accessStudentDataAPI(@RequestBody Map<String, Object> params){
        LOGGER.info("accessStudentDataAPI v1 API hit with params : {}", params);
        return AccessStudentData.accessStudentData(params);
    }

    @PostMapping("/accessData")
    @Operation(
            operationId = "Access Students Database",
            description = "Allows management of data for students"
    )
    public Map<String, Object> accessStudentDataAPI(@RequestBody AccessStudentDataDTO accessStudentDataDTO){
        Map<String, Object> params = new HashMap<>();
        if(accessStudentDataDTO.getStudentManagementAction() != null)params.put(JsonInputFields.STUDENT_MANAGEMENT_ACTION.getValue(), accessStudentDataDTO.getStudentManagementAction().toString());
        if(accessStudentDataDTO.getId() != null)params.put(JsonInputFields.ENTITY_ID.getValue(), accessStudentDataDTO.getId());
        if(accessStudentDataDTO.getName() != null)params.put(JsonInputFields.ENTITY_NAME.getValue(), accessStudentDataDTO.getName());
        LOGGER.info("accessStudentDataAPI API hit with params : {}", params);
        return AccessStudentData.accessStudentData(params);
    }

    @GetMapping("/accessData/possibleActions")
    @Operation(
            operationId = "List Possible actions for accessStudentDataAPI Json input"
    )
    public Set<String> listPossibleActions(){
        Set<String> output = new HashSet<>();
        for(AccessStudentData.StudentManagementAction field : AccessStudentData.StudentManagementAction.values()){
            output.add(field.getValue()) ;
        }
        return output;
    }
}
