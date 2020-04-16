package ua.com.foxminded.task_7_SQL.jdbc;

import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;
import ua.com.foxminded.task_7_SQL.exeptions.QueryExecutionException;

import java.sql.*;

public class CourseDao {

    public final static String ADD_STUDENT_TO_COURSE_QUERY = "INSERT INTO course_student (course_id, student_id) VALUES (?,?)";
    public final static String CREATE_CORUSE_QUERY = "INSERT INTO courses(course_name,course_description) VALUES(?,?)";


    public boolean addStudentToCourse(int courseID, int studentID) throws QueryExecutionException {
        boolean rowAffected = false;
        try (Connection connection = ConnectorDB.getConnect();
             PreparedStatement pstmt = connection.prepareStatement(ADD_STUDENT_TO_COURSE_QUERY)) {

            pstmt.setInt(1, courseID);
            pstmt.setInt(2, studentID);
            int rowsAdded = pstmt.executeUpdate();
            if (rowsAdded > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            throw new QueryExecutionException("problem with adding student to course", e);
        }
        return rowAffected;
    }

    public int createCourse(String courseName, String description) throws QueryExecutionException {
        int courseId = 0;

        try (Connection con = ConnectorDB.getConnect();
             PreparedStatement pstmt = con.prepareStatement(CREATE_CORUSE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, courseName);
            pstmt.setString(2, description);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    courseId = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new QueryExecutionException("problem with adding student to course", e);
        }
        return courseId;
    }
}
