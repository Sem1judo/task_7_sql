package ua.com.foxminded.task_7_SQL.jdbc;

import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;
import ua.com.foxminded.task_7_SQL.domain.Student;
import ua.com.foxminded.task_7_SQL.exeptions.QueryExecutionException;

import java.sql.*;
import java.util.*;

public class StudentDao {

    public static final String CREATE_STUDENT_QUERY = "INSERT INTO students(first_name,last_name) VALUES(?,?)";
    public static final String DELETE_STUDENT_BY_ID_QUERY = "DELETE FROM students WHERE student_id = ?";
    public static final String FIND_STUDENTS_FROM_COURSE_BY_NAME_QUERY = "SELECT c.course_id, c.course_name," +
            "s.student_id,s.first_name,s.last_name from students as s\n" +
            "     INNER JOIN course_student as cs ON cs.student_id = s.student_id\n" +
            "     INNER JOIN courses as c ON cs.course_id = c.course_id\n" +
            "     where c.course_name LIKE  ?;";

    public int createStudentWithGenerateKey(String firstName, String lastName) throws QueryExecutionException {
        int studentId = 0;

        try (Connection con = ConnectorDB.getConnect();
             PreparedStatement pstmt = con.prepareStatement(CREATE_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    studentId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new QueryExecutionException("Problem with creating new Student", ex);
        }
        return studentId;
    }

    public boolean deleteStudentById(int id) throws QueryExecutionException {

        boolean rowDeleted;
        try (Connection con = ConnectorDB.getConnect();
             PreparedStatement pstmt = con.prepareStatement(DELETE_STUDENT_BY_ID_QUERY)) {

            pstmt.setInt(1, id);
            rowDeleted = pstmt.executeUpdate() > 0;

        } catch (SQLException e1) {
            throw new QueryExecutionException("Can't delete student", e1);
        }
        return rowDeleted;
    }


    public String findStudentsFromCourse(String courseName) throws QueryExecutionException {
        StringBuilder studentsOnCourse = new StringBuilder();
        ResultSet rs;
        try (Connection con = ConnectorDB.getConnect();
             PreparedStatement pstmt = con.prepareStatement(FIND_STUDENTS_FROM_COURSE_BY_NAME_QUERY)) {

            pstmt.setString(1, "%" + courseName + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                studentsOnCourse.append(firstName).append(" ").append(lastName).append("\n");
            }
        } catch (SQLException e) {
            throw new QueryExecutionException("Can't find students", e);
        }
        return studentsOnCourse.toString();
    }
}

