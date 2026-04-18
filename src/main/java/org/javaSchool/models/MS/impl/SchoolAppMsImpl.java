package org.javaSchool.models.MS.impl;

import org.javaSchool.models.MS.SchoolAppHelperMs;
import org.javaSchool.models.MS.SchoolAppMs;
import org.javaSchool.models.MS.utils.MsDiConstructor;
import org.javaSchool.models.MS.utils.MsImplMetadata;
import org.javaSchool.utils.jsonIO.JsonInputFields;

import java.util.HashSet;
import java.util.Set;

@MsImplMetadata(
        value = SchoolAppMs.class,
        description = "Service for basic API endpoints"
)
public class SchoolAppMsImpl implements SchoolAppMs {
    SchoolAppHelperMs schoolAppHelperMs;

    @MsDiConstructor
    public SchoolAppMsImpl(SchoolAppHelperMs schoolAppHelperMs){
        this.schoolAppHelperMs = schoolAppHelperMs;
    }

    public String handleHealth(){
        return schoolAppHelperMs.handleHealth();
    }

    public Set<String> handleGetPossibleInputFields(){
        Set<String> output = new HashSet<>();
        for (JsonInputFields field : JsonInputFields.values()) {
            output.add(field.getValue()) ;
        }
        return output;
    }
}
