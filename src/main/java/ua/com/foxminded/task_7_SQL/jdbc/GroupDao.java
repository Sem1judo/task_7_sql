package ua.com.foxminded.task_7_SQL.jdbc;

import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;
import ua.com.foxminded.task_7_SQL.exeptions.QueryExecutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDao {

    public static final String FIND_GROUPS_BY_COUNT = "SELECT groups.group_name, COUNT(students.group_id) as students_quantity FROM groups\n" +
            "INNER JOIN students ON groups.group_id = students.group_id\n" +
            "GROUP BY group_name\n" +
            "HAVING  COUNT(students.group_id) <=?";

    public String findGroupsByCount(int count) throws QueryExecutionException {
        StringBuilder groups = new StringBuilder();
        try (Connection connection = ConnectorDB.getConnect();
             PreparedStatement pstmt = connection.prepareStatement(FIND_GROUPS_BY_COUNT)) {

            pstmt.setInt(1, count);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String groupName = rs.getString("group_name");
                String studentsQuantity = rs.getString("students_quantity");
                groups.append(groupName).append(": ").append(studentsQuantity).append("\n");
            }

        } catch (SQLException e) {
            throw new QueryExecutionException("problem with finding groups", e);
        }
        return groups.toString();
    }
}


