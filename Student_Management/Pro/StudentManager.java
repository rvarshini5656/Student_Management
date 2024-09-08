import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.ser";
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(String id, String name, String grades) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setGrades(grades);
            saveStudents();
        }
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        saveStudents();
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, so we start with an empty list
            students = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
