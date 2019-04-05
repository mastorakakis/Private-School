package methods;

import entities.Assignment;
import entities.Course;
import entities.Student;
import entities.Trainer;
import java.util.List;

public class Print {

    public static void course(List<Course> list) {
        System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                "ID", "TITLE", "STREAM", "TYPE", "DURATION");
        for (Course course : list) {
            System.out.println(course);
        }
    }

    public static void student(List<Student> list) {
        System.out.printf("%-5s %-13s %-17s %-13s %s\n",
                "ID", "FIRST NAME", "LAST NAME", "BIRTH DATE", "FEES");
        for (Student student : list) {
            System.out.println(student);
        }
    }

    public static void trainer(List<Trainer> list) {
        System.out.printf("%-5s %-13s %-17s %s\n",
                "ID", "FIRST NAME", "LAST NAME", "SUBJECT");
        for (Trainer trainer : list) {
            System.out.println(trainer);
        }
    }

    public static void assignment(List<Assignment> list) {
        for (Assignment assignment : list) {
            System.out.println(assignment);
        }
    }
}
