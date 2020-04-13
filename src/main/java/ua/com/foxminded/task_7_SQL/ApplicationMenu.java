package ua.com.foxminded.task_7_SQL;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.foxminded.task_7_SQL.jdbc.GroupDao;
import ua.com.foxminded.task_7_SQL.jdbc.StudentDao;
import ua.com.foxminded.task_7_SQL.jdbc.СourseDao;
import ua.com.foxminded.task_7_SQL.runnerSQL.RunnerScriptSQL;

import java.io.File;
import java.util.Scanner;

public class ApplicationMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDao studentDao = new StudentDao();
    private static GroupDao groupDao = new GroupDao();
    private static СourseDao courseDao = new СourseDao();
    private static RunnerScriptSQL runnerScriptSQL = new RunnerScriptSQL();
    public static final String SCRIPT_SQL = new File("src/main/resources/schoolDB.sql").getAbsolutePath();


    public static void main(String[] args) {

        int choice;
        int studentId;

        runnerScriptSQL.runScriptSQL(SCRIPT_SQL);

        contentMenu();
        choice = scanner.nextInt();


        switch (choice) {
            case 1:
                System.out.println("Please enter quantity of students");
                int quantity = scanner.nextInt();
                System.out.println(groupDao.findGroupsByCount(quantity));
                break;

            case 2:
                System.out.println("Please enter name of course");
                String courseName = scanner.next();
                System.out.println(studentDao.findStudentsFromCourse(courseName));
                break;

            case 3:
                System.out.println("Please enter first name ");
                String firstName = scanner.next();
                System.out.println("Please enter first name ");
                String lastName = scanner.next();
                studentId = studentDao.createStudentWithGenerateKey(firstName, lastName);
                System.out.println("Student was created with id = " + studentId);
                break;

            case 4:
                System.out.println("Please enter id student who want to delete ");
                studentId = scanner.nextInt();
                studentDao.deleteStudentById(studentId);
                break;

            case 5:
                System.out.println("Please enter id student who want to add to course ");
                studentId = scanner.nextInt();
                System.out.println("Please enter id course which want to add to student ");
                int idCourse = scanner.nextInt();
                courseDao.addStudentToCourse(idCourse, studentId);
                break;

            case 6:
                System.out.println("Please enter id student who want to delete from course");
                studentId = scanner.nextInt();
                studentDao.deleteStudentById(studentId);
                break;

        }

    }

    public static void contentMenu() {
        System.out.println("To find all groups with less or equals student count, press - 1");
        System.out.println("Find all students related to course, press - 2");
        System.out.println("Add new student, press - 3");
        System.out.println("Delete student by id, press - 4");
        System.out.println("Add student to course, press - 5");
        System.out.println("Remove the student from one of his courses, press - 6");
    }
}
