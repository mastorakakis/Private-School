package joinedEntities;

import entities.Course;
import entities.Trainer;
import java.util.List;

public class TrainerPerCourse {

    private Course course;
    private List<Trainer> trainers;

    public TrainerPerCourse() {
    }

    public TrainerPerCourse(Course course, List<Trainer> courses) {
        this.course = course;
        this.trainers = courses;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TrainerPerCourse{" + "course=" + course + ", trainers=" + trainers + '}';
    }

}
