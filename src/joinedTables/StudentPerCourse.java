package joinedTables;

import entities.Course;
import entities.Student;

public class StudentPerCourse {

    private Student student;
    private Course course;

    public StudentPerCourse() {
    }

    public StudentPerCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
