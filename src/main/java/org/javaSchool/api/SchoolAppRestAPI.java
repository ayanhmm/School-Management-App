package org.javaSchool.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javaschool")
public class SchoolAppRestAPI {

    @RequestMapping("/health")
    public String health(){
        return "Java School API running";
    }
}
