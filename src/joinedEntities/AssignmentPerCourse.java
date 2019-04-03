package joinedEntities;

import entities.Assignment;
import entities.Course;
import java.util.List;

public class AssignmentPerCourse {

    private Course course;
    private List<Assignment> assigmnents;

    public AssignmentPerCourse() {
    }

    public AssignmentPerCourse(Course course, List<Assignment> assigmnents) {
        this.course = course;
        this.assigmnents = assigmnents;
    }

    public List<Assignment> getAssigmnents() {
        return assigmnents;
    }

    public void setAssigmnents(List<Assignment> assigmnents) {
        this.assigmnents = assigmnents;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "AssignmentPerCourse{" + "course=" + course + ", assigmnents=" + assigmnents + '}';
    }
}
