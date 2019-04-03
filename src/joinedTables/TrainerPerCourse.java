package joinedTables;

import entities.Course;
import entities.Trainer;

public class TrainerPerCourse {

    private Trainer trainer;
    private Course course;

    public TrainerPerCourse() {
    }

    public TrainerPerCourse(Trainer trainer, Course course) {
        this.trainer = trainer;
        this.course = course;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
