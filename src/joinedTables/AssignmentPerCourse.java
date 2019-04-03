package joinedTables;

import entities.Assignment;
import entities.Course;

public class AssignmentPerCourse {

    private Assignment assignment;
    private Course course;

    public AssignmentPerCourse() {
    }

    public AssignmentPerCourse(Assignment assignment, Course course) {
        this.assignment = assignment;
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
