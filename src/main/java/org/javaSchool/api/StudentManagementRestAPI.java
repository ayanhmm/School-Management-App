package org.javaSchool.api;

import io.swagger.v3.oas.annotations.Operation;
import org.javaSchool.api.utils.ApiExecutor;
import org.javaSchool.models.MS.StudentManagementMs;
import org.javaSchool.models.MS.impl.StudentManagementMsImpl;
import org.springframework.web.bind.annotation.*;

import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.studentManagement.AccessStudentData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/javaschool/student")
public class StudentManagementRestAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementRestAPI.class);
    private static final Class<StudentManagementMs> SERVICE_CLASS = StudentManagementMs.class;

    @PostMapping("/accessData/v1")
    @Operation(
            operationId = "Access Students Database",
            description = "Allows management of data for students"
    )
    @Deprecated(since = "new method introduced to handle accessStudentDataDTO input")
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
        LOGGER.debug("accessStudentDataAPI API hit with params : {}", accessStudentDataDTO.toString());
        return ApiExecutor.invokeService(SERVICE_CLASS, service -> service.handleAccessStudentDataAPI(accessStudentDataDTO));
    }

    @GetMapping("/accessData/possibleActions")
    @Operation(
            operationId = "List Possible actions for accessStudentDataAPI Json input"
    )
    public Set<String> listPossibleActions(){
        return ApiExecutor.invokeService(SERVICE_CLASS, service -> service.handleListPossibleActions());
    }
}
