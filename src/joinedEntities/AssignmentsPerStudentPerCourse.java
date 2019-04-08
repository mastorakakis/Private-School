package joinedEntities;

import entities.Assignment;
import entities.Course;
import entities.Student;
import java.util.List;

public class AssignmentsPerStudentPerCourse {

    private Course course;
    private List<Assignment> assignmes;
    private List<Student> students;

    public AssignmentsPerStudentPerCourse() {
    }

    public AssignmentsPerStudentPerCourse(Course course, List<Assignment> assignmes, List<Student> students) {
        this.course = course;
        this.assignmes = assignmes;
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Assignment> getAssignmes() {
        return assignmes;
    }

    public void setAssignmes(List<Assignment> assignmes) {
        this.assignmes = assignmes;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
