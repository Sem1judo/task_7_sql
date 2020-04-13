package ua.com.foxminded.task_7_SQL.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {

    StudentDao studentDao;

    @BeforeEach
    void init() {
        studentDao = new StudentDao();
    }

    @Test
    void addStudentShouldAddStudent(){
        int actual = studentDao.createStudentWithGenerateKey("stas","sem");
        int expected = 17;
        assertEquals(expected,actual);
    }


}