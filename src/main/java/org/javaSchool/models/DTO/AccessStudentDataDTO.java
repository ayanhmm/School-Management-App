package org.javaSchool.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.utils.JsonFieldsTextValues;


public class AccessStudentDataDTO {
    AccessStudentData.StudentManagementAction studentManagementAction;

    @JsonProperty(JsonFieldsTextValues.ENTITY_ID)
    private Integer id;

    @JsonProperty(JsonFieldsTextValues.ENTITY_NAME)
    String name;

    public AccessStudentData.StudentManagementAction getStudentManagementAction() {
        return studentManagementAction;
    }
    public void setStudentManagementAction(AccessStudentData.StudentManagementAction studentManagementAction) {
        this.studentManagementAction = studentManagementAction;
    }

    @JsonProperty(JsonFieldsTextValues.STUDENT_MANAGEMENT_ACTION)
    public void setStudentManagementAction(String studentManagementAction) {
        this.studentManagementAction = AccessStudentData.StudentManagementAction.fromString(studentManagementAction);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "DTO { " +
                "Id = " +  id +
                ";Name = " +  name +
                ";studentManagementAction = " +  studentManagementAction.getValue() +
                " }";
    }
}
