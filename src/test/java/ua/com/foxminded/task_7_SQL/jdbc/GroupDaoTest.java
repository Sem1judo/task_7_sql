package ua.com.foxminded.task_7_SQL.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.task_7_SQL.exeptions.QueryExecutionException;

import static org.junit.jupiter.api.Assertions.*;


class GroupDaoTest {
    GroupDao groupDao;

    @BeforeEach
    void init() {
        groupDao = new GroupDao();
    }

    @Test
    void findGroupsByCountShouldOutputQuantityOfStudents() {
        String expected = "algebra: 2\n" +
                "math: 3\n" +
                "biology: 1\n";
        String actual = groupDao.findGroupsByCount(30);
        assertEquals(expected, actual);
    }

    @Test
    void findGroupsByCountShouldOutputZeroOfStudents() {
        String expected = "";
        String actual = groupDao.findGroupsByCount(0);
        assertEquals(expected, actual);
    }
}