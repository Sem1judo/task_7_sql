package ua.com.foxminded.task_7_SQL.jdbc;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.FileInputStream;

class StudentDaoTest extends DBTestCase {

    StudentDao studentDao;
    CourseDao courseDao;

    @BeforeEach
    void init() {
        studentDao = new StudentDao();
        courseDao = new CourseDao();
    }

    public StudentDaoTest() {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql:school");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "testUser");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, 97);
        config.setFeature(DatabaseConfig.FEATURE_BATCHED_STATEMENTS, true);
    }

    @Test
    public void createStudentWithGenerateKeyShouldCreateStudent() {
        int actual = studentDao.createStudentWithGenerateKey("firstName1", "lastName1");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void deleteStudentByIShouldDeleteStudent() {
        int studentId_1 = studentDao.createStudentWithGenerateKey("firstName1", "lastName1");
        assertTrue(studentDao.deleteStudentById(studentId_1));
    }

    @Test
    public void findStudentsFromCourseShouldOutputAllStudentsRelatedToCourse() {
        int studentId_1 = studentDao.createStudentWithGenerateKey("firstName1", "lastName1");
        int studentId_2 = studentDao.createStudentWithGenerateKey("firstName2", "lastName2");
        int studentId_3 = studentDao.createStudentWithGenerateKey("firstName3", "lastName3");
        int courseId_1 = courseDao.createCourse("Mathematics", "Science about figures");
        String actual = studentDao.findStudentsFromCourse("Mathematics");
        String expected = "firstName1 lastName1\n" +
                "firstName2 lastName2\n" +
                "firstName3 lastName3\n";

        assertEquals(expected, actual);
    }


}