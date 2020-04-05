package ua.com.foxminded.task_7_SQL.domain;



import javax.persistence.Entity;
import java.util.*;

@Entity
public class Group {
    private int id;
    private String name;
    private List<Student> students;




    public Group() {
    }

    public Group( String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
