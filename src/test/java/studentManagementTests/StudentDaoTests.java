package studentManagementTests;

import org.javaSchool.configs.reader.ConfigReader;
import org.javaSchool.studentManagement.AccessStudentData;
import org.javaSchool.studentManagement.Student;
import org.javaSchool.models.DAO.StudentDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StudentDaoTests {

    @Test
    public void studentDaoTest(){
        ConfigReader.init();
        AccessStudentData.StudentManagementAction action = AccessStudentData.StudentManagementAction.DISPLAY_ALL_STUDENTS;
        Student student = new Student();
        Map<String, Object> output =  StudentDao.execute(action, student);
        Assertions.assertNotNull(output);
    }
}
