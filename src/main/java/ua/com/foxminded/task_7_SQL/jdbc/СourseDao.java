package ua.com.foxminded.task_7_SQL.jdbc;

import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;
import ua.com.foxminded.task_7_SQL.exeptions.QueryExecutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class СourseDao {

    public final static String ADD_STUDENT_TO_COURSE_QUERY = "INSERT INTO course_student (course_id, student_id) VALUES (?,?)";

    public void addStudentToCourse(int courseID, int studentID) throws QueryExecutionException {
        try (Connection connection = ConnectorDB.getConnect();
             PreparedStatement pstmt = connection.prepareStatement(ADD_STUDENT_TO_COURSE_QUERY)) {

            pstmt.setInt(1, courseID);
            pstmt.setInt(2, studentID);
            int rowsAdded = pstmt.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("A student was added successfully!");
            }
        } catch (SQLException e) {
            throw new QueryExecutionException("problem with adding student to course", e);
        }
    }
}
