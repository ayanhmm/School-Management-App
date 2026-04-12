package org.javaSchool.models.MS.impl;

import org.javaSchool.models.MS.SchoolAppMs;
import org.javaSchool.models.MS.utils.MsImplMetadata;
import org.javaSchool.utils.jsonIO.JsonInputFields;

import java.util.HashSet;
import java.util.Set;

@MsImplMetadata(
        value = SchoolAppMs.class,
        description = "Service for basic API endpoints"
)
public class SchoolAppMsImpl implements SchoolAppMs {

    public String handleHealth(){
        return "Java School API running";
    }

    public Set<String> handleGetPossibleInputFields(){
        Set<String> output = new HashSet<>();
        for (JsonInputFields field : JsonInputFields.values()) {
            output.add(field.getValue()) ;
        }
        return output;
    }
}
