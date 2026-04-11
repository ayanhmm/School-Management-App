package org.javaSchool.api;

import io.swagger.v3.oas.annotations.Operation;
import org.javaSchool.models.MS.AccessStudentDataService;
import org.springframework.web.bind.annotation.*;

import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.studentManagement.AccessStudentData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LOGGER.info("accessStudentDataAPI API hit with params : {}", accessStudentDataDTO.toString());
        return AccessStudentDataService.handle(accessStudentDataDTO);
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
