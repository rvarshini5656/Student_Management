import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String grades;

    public Student(String name, String id, String grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGrades() {
        return grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Grades: " + grades;
    }
}
