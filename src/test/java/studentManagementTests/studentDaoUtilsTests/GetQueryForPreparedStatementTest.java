package studentManagementTests.studentDaoUtilsTests;

import org.javaSchool.configs.reader.ConfigReader;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.studentManagement.studentDaoUtils.GetQueryForPreparedStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetQueryForPreparedStatementTest {

    @Test
    public void getQueryForPreparedStatementTest(){
        ConfigReader.init();
        AccessStudentData.StudentManagementAction action = AccessStudentData.StudentManagementAction.UPDATE_STUDENT;
        String output = GetQueryForPreparedStatement.getQueryForPreparedStatement(action);
        Assertions.assertNotNull(output);
    }
}
