package ua.com.foxminded.task_7_SQL.DATA;


//b. Generate test data:
//
//        * 10 groups with randomly generated names. The name should contain 2 characters, hyphen, 2 numbers
//
//        * Create 10 courses (math, biology, etc)
//
//        * 200 students. Take 20 first names and 20 last names and randomly combine them to generate students.
//
//        * Randomly assign students to groups. Each group could contain from 10 to 30 students. It is possible that some groups will be without students or students without groups
//
//        * Create relation MANY-TO-MANY between tables STUDENTS and COURSES. Randomly assign from 1 to 3 courses for each student


import org.apache.commons.lang3.RandomStringUtils;
import ua.com.foxminded.task_7_SQL.domain.Course;
import ua.com.foxminded.task_7_SQL.domain.Group;
import ua.com.foxminded.task_7_SQL.domain.Student;

import java.util.*;

public class GeneratedData {

    private static Random random = new Random();


    public static String[] generateStudentName() {
        String[] results = new String[2];
        results[0] = firstName[random.nextInt(firstName.length)];
        results[1] = lastName[random.nextInt(lastName.length)];
        return results;
    }


    private static final String[] firstName = new String[]{"Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred",
            "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew"};

    private static final String[] lastName = new String[]{"Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon",
            "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson"};


    public static List<Group> getGroups() {


        Group group1 = new Group(generateGroupName());
        Group group2 = new Group(generateGroupName());
        Group group3 = new Group(generateGroupName());
        Group group4 = new Group(generateGroupName());
        Group group5 = new Group(generateGroupName());
        Group group6 = new Group(generateGroupName());
        Group group7 = new Group(generateGroupName());
        Group group8 = new Group(generateGroupName());
        Group group9 = new Group(generateGroupName());
        Group group10 = new Group(generateGroupName());

        List students1 = assignGroupStudents();
        group1.setStudents(students1);

        List students2 = assignGroupStudents();
        group2.setStudents(students2);

        List students3 = assignGroupStudents();
        group3.setStudents(students3);

        List students4 = assignGroupStudents();
        group4.setStudents(students4);

        List students5 = assignGroupStudents();
        group5.setStudents(students5);

        List students6 = assignGroupStudents();
        group6.setStudents(students6);

        List students7 = assignGroupStudents();
        group7.setStudents(students7);

        List students8 = assignGroupStudents();
        group8.setStudents(students8);

        List students9 = assignGroupStudents();
        group9.setStudents(students9);

        List students10 = assignGroupStudents();
        group10.setStudents(students10);


        List<Group> groups = new ArrayList<>();

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);
        groups.add(group5);
        groups.add(group6);
        groups.add(group7);
        groups.add(group8);
        groups.add(group9);
        groups.add(group10);


        return groups;
    }

    public static List<Course> getCourses() {

        List<Course> courses = new ArrayList<>();

        Course course1 = new Course("Math");
        Course course2 = new Course("Biology");
        Course course3 = new Course("Accounting");
        Course course4 = new Course(" Biological Sciences");
        Course course5 = new Course("Engineering");
        Course course6 = new Course("Psychology");
        Course course7 = new Course("English");
        Course course8 = new Course("Architecture");
        Course course9 = new Course("Law");
        Course course10 = new Course("Mechanical");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);
        courses.add(course9);
        courses.add(course10);


        return courses;
    }

    public static List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            students.add(new Student(generateStudentName()[0], generateStudentName()[1]));
        }
        for (int i = 0; i < students.size(); i++) {
            students.get(i).setCourses(assignStudentsCourses());
        }

        return students;
    }

    public static List assignGroupStudents() {
        List<Optional<Student>> students = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            students.add(Optional.ofNullable(getStudents().get(random.nextInt(200))));
        }

        return students;
    }

    public static List<Course> assignStudentsCourses() {
        List<Course> course = new ArrayList<>();
        for (int i = 0; i < random.nextInt(3); i++) {
            course.add(getCourses().get(random.nextInt(10)));
        }

        return course;
    }


    private static String generateGroupName() {

        StringBuilder randomName = new StringBuilder();
        String letters = RandomStringUtils.randomAlphabetic(2).toLowerCase();
        String numbers = RandomStringUtils.randomNumeric(2);

        randomName.append(letters).append("-").append(numbers);

        return randomName.toString().toLowerCase();
    }

}
