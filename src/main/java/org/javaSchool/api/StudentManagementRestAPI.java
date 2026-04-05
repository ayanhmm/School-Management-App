package org.javaSchool.api;

import io.swagger.v3.oas.annotations.Operation;
import org.javaSchool.studentManagement.AccessStudentData;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import java.util.Map;

@RestController
@RequestMapping("/javaschool/student")
public class StudentManagementRestAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementRestAPI.class);

    @PostMapping
    @RequestMapping("/accessData")
    @Operation(
            operationId = "Access Students Database",
            description = "Allows management of data for students"
    )
    public Map<String, Object> accessStudentDataAPI(@RequestBody Map<String, Object> params){
        LOGGER.info("accessStudentDataAPI API hit with params : {}", params);
        return AccessStudentData.accessStudentData(params);
    }
}
