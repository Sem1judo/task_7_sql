package ua.com.foxminded.task_7_SQL.generetedData;

import org.apache.commons.lang3.RandomStringUtils;
import ua.com.foxminded.task_7_SQL.domain.Course;
import ua.com.foxminded.task_7_SQL.domain.Group;
import ua.com.foxminded.task_7_SQL.domain.Student;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class GeneratedData {
    private static Random random = new Random();

    private static final List<String> firstNames;
    private static final List<String> lastNames;
    private static final List<String> courseNames;

    static {
        courseNames = new ArrayList<>();

        courseNames.add("Math");
        courseNames.add("English");
        courseNames.add("Biology");
        courseNames.add("Accounting");
        courseNames.add("Biological Sciences");
        courseNames.add("Psychology");
        courseNames.add("English");
        courseNames.add("Mechanical");
        courseNames.add("Law");
        courseNames.add("Technology");
        

        firstNames = new ArrayList<>();

        firstNames.add("Adam");
        firstNames.add("Alex");
        firstNames.add("Aaron");
        firstNames.add("Ben");
        firstNames.add("Carl");
        firstNames.add("Dan");
        firstNames.add("David");
        firstNames.add("Edward");
        firstNames.add("Fred");
        firstNames.add("Frank");
        firstNames.add("George");
        firstNames.add("Hal");
        firstNames.add("Hank");
        firstNames.add("Ike");
        firstNames.add("John");
        firstNames.add("Jack");
        firstNames.add("Joe");
        firstNames.add("Larry");
        firstNames.add("Monte");
        firstNames.add("Matthew");

        lastNames = new ArrayList<>();

        lastNames.add("Anderson");
        lastNames.add("Ashwoon");
        lastNames.add("Aikin");
        lastNames.add("Bateman");
        lastNames.add("Bongard");
        lastNames.add("Bowers");
        lastNames.add("Boyd");
        lastNames.add("Cannon");
        lastNames.add("Cast");
        lastNames.add("Deitz");
        lastNames.add("Dewalt");
        lastNames.add("Ebner");
        lastNames.add("Frick");
        lastNames.add("Hancock");
        lastNames.add("Haworth");
        lastNames.add("Hesch");
        lastNames.add("Hoffman");
        lastNames.add("Kassing");
        lastNames.add("Knutson");
        lastNames.add("Sorokov");

    }

    private static final int LIMIT_NAMES = 20;
    private static final int LIMIT_STUDENTS = 200;
    private static final int LIMIT_COURSES_FOR_STUDENT = 3;
    private static final int LIMIT_GROUP = 30;
    private static final int LIMIT_COURSES = 10;


    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();

        IntStream.iterate(0, i -> i++)
                .limit(10)
                .forEach(i -> groups.add(new Group(generateGroupName(), assignGroupStudents())));

        return groups;
    }

    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        AtomicInteger iteratorCourseNames = new AtomicInteger();

        IntStream.iterate(0, i -> i++)
                .limit(LIMIT_COURSES)
                .forEach(i -> courses.add(new Course(courseNames.get(iteratorCourseNames.getAndIncrement()))));

        return courses;
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        IntStream.iterate(0, i -> i++).limit(LIMIT_STUDENTS)
                .forEach(i -> students.add(getRandomStudent()));

        IntStream.rangeClosed(0, students.size() - 1)
                .forEach(i -> students.get(i).setCourses(assignStudentsCourses()));

        return students;
    }

    private List<Course> assignStudentsCourses() {
        List<Course> courses = new ArrayList<>();

        IntStream.rangeClosed(0, ThreadLocalRandom.current().nextInt(0, LIMIT_COURSES_FOR_STUDENT + 1))
                .forEach(i -> courses.add(getCourses().get(random.nextInt(10))));

        return courses;
    }

    private List assignGroupStudents() {
        List<Optional<Student>> students = new ArrayList<>();

        IntStream.rangeClosed(0, ThreadLocalRandom.current().nextInt(0, LIMIT_GROUP + 1))
                .forEach(i -> students.add(Optional.ofNullable(getStudents().get(random.nextInt(LIMIT_STUDENTS + 1)))));

        return students;
    }

    private String generateGroupName() {
        StringBuilder randomName = new StringBuilder();

        String letters = RandomStringUtils.randomAlphabetic(2).toLowerCase();
        String numbers = RandomStringUtils.randomNumeric(2);

        randomName.append(letters).append("-").append(numbers);

        return randomName.toString().toLowerCase();
    }

    private Student getRandomStudent() {
        String studentName = firstNames.get(random.nextInt(LIMIT_NAMES));
        String lastName = lastNames.get(random.nextInt(LIMIT_NAMES));

        return new Student(studentName, lastName);
    }

}

