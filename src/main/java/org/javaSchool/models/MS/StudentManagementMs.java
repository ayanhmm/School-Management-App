package org.javaSchool.models.MS;

import org.javaSchool.models.DTO.AccessStudentDataDTO;
import org.javaSchool.models.MS.impl.StudentManagementMsImpl;
import org.javaSchool.models.MS.utils.MsImplMetadata;

import java.util.Map;
import java.util.Set;

public interface StudentManagementMs extends MicroService {

    public Map<String, Object> handleAccessStudentDataAPI(AccessStudentDataDTO accessStudentDataDTO);
    public Set<String> handleListPossibleActions();

}
