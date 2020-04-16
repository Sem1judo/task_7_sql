package ua.com.foxminded.task_7_SQL.jdbc;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class GroupDaoTest {
    GroupDao groupDao;
    StudentDao studentDao;

    @BeforeEach
    void init() {
        groupDao = new GroupDao();
        studentDao = new StudentDao();
    }

    public GroupDaoTest() {
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
    void findGroupsByCountShouldOutputQuantityOfStudents() {
        String expected = "fb-12: 3\n" +
                "aa-31: 2\n";
        String actual = groupDao.findGroupsByCount(30);
        assertEquals(expected, actual);
    }

}