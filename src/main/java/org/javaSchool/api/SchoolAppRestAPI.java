package org.javaSchool.api;

import org.javaSchool.utils.JsonInputFields;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/javaschool")
public class SchoolAppRestAPI {

    @RequestMapping("/health")
    public String health(){
        return "Java School API running";
    }

    @RequestMapping("/possibleInputFields")
    public Set<String> getPossibleInputFields(){
        Set<String> output = new HashSet<>();
        for (JsonInputFields field : JsonInputFields.values()) {
            output.add(field.getValue()) ;
        }
        return output;
    }
}
