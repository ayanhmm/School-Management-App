package org.javaSchool.api;

import org.javaSchool.api.utils.ApiExecutor;
import org.javaSchool.models.MS.SchoolAppMs;
import org.javaSchool.models.MS.StudentManagementMs;
import org.javaSchool.utils.jsonIO.JsonInputFields;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/javaschool")
public class SchoolAppRestAPI {
    static final Class<SchoolAppMs> SERVICE_CLASS = SchoolAppMs.class;

    @RequestMapping("/health")
    public String health(){
        return ApiExecutor.invokeService(SERVICE_CLASS, service -> service.handleHealth());
    }

    @RequestMapping("/possibleInputFields")
    public Set<String> getPossibleInputFields(){
        return ApiExecutor.invokeService(SERVICE_CLASS, service -> service.handleGetPossibleInputFields());
    }
}
