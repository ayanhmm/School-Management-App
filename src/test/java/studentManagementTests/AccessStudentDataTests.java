package studentManagementTests;

import org.javaSchool.studentManagement.AccessStudentData.StudentManagementAction;
import static org.javaSchool.studentManagement.AccessStudentData.accessStudentData;

import org.javaSchool.utils.JsonOutputFields;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AccessStudentDataTests {

    @Test
    void accessStudentDataTest_AddStudent(){
        Map<String, Object> params = new HashMap<>();
        params.put("Action", StudentManagementAction.ADD_STUDENT.toString());
        params.put("ID", 1);
        params.put("Name", "Ayan");

        Map<String, Object> output = accessStudentData(params);
        Assertions.assertEquals(output.get(JsonOutputFields.SUCCESS_BOOLEAN.getValue()), true);
    }

    @Test
    void accessStudentDataTest_DeleteStudent(){
        Map<String, Object> params = new HashMap<>();
        params.put("Action", StudentManagementAction.DELETE_STUDENT.toString());
        params.put("ID", 1);
        params.put("Name", "Ayan");

        Map<String, Object> output = accessStudentData(params);
        Assertions.assertEquals(output.get(JsonOutputFields.SUCCESS_BOOLEAN.getValue()), true);
    }

    @Test
    void accessStudentDataTest_DisplayStudent(){
        Map<String, Object> params = new HashMap<>();
        params.put("Action", StudentManagementAction.DISPLAY_STUDENT.toString());
        params.put("ID", 1);
        params.put("Name", "Ayan");

        Map<String, Object> output = accessStudentData(params);
        Assertions.assertEquals(output.get(JsonOutputFields.SUCCESS_BOOLEAN.getValue()), true);
    }
}
