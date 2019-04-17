package joinedEntities;

import entities.Assignment;
import entities.Course;
import java.util.List;

public class AssignmentsPerCourse {

    private Course course;
    private List<Assignment> assignments;

    public AssignmentsPerCourse() {
    }

    public AssignmentsPerCourse(Course course, List<Assignment> assigmnents) {
        this.course = course;
        this.assignments = assigmnents;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "AssignmentPerCourse{" + "course=" + course + ", assigmnents=" + assignments + '}';
    }
}
