package joinedEntities;

import entities.Course;
import entities.Student;
import java.util.List;

public class StudentPerCourse {

    private Course course;
    private List<Student> students;

    public StudentPerCourse() {
    }

    public StudentPerCourse(Course course, List<Student> student) {
        this.course = course;
        this.students = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentPerCourse{" + "course=" + course + ", students=" + students + '}';
    }
}
