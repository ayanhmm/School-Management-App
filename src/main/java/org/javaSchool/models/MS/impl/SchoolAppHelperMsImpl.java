package org.javaSchool.models.MS.impl;

import org.javaSchool.models.MS.SchoolAppHelperMs;
import org.javaSchool.models.MS.SchoolAppMs;
import org.javaSchool.models.MS.utils.MsImplMetadata;

@MsImplMetadata(
        value = SchoolAppHelperMs.class
)
public class SchoolAppHelperMsImpl implements SchoolAppHelperMs {
    public String handleHealth(){
        return "Java School API running";
    }

}
