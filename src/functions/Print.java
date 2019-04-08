package functions;

import entities.Assignment;
import entities.Course;
import entities.Student;
import entities.Trainer;
import java.util.List;
import joinedEntities.AssignmentsPerCourse;
import joinedEntities.StudentsPerCourse;
import joinedEntities.TrainersPerCourse;

public class Print {

    public static void courses(List<Course> list) {
        System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                "ID", "TITLE", "STREAM", "TYPE", "DURATION");
        for (Course course : list) {
            System.out.println(course);
        }
    }

    public static void students(List<Student> list) {
        System.out.printf("%-5s %-13s %-17s %-13s %s\n",
                "ID", "FIRST NAME", "LAST NAME", "BIRTH DATE", "FEES");
        for (Student student : list) {
            System.out.println(student);
        }
    }

    public static void trainers(List<Trainer> list) {
        System.out.printf("%-5s %-13s %-17s %s\n",
                "ID", "FIRST NAME", "LAST NAME", "SUBJECT");
        for (Trainer trainer : list) {
            System.out.println(trainer);
        }
    }

    public static void assignments(List<Assignment> list) {
        for (Assignment assignment : list) {
            System.out.println(assignment);
        }
    }

    public static void studentsPerCourse(List<StudentsPerCourse> studentsPerCourse) {
        for (StudentsPerCourse spc : studentsPerCourse) {
            System.out.println("");
            System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                    "C-ID", "TITLE", "STREAM", "TYPE", "DURATION");
            System.out.println(spc.getCourse());
            System.out.println("--------------------------------------------------------------");
            System.out.printf("\t%-5s %-13s %-17s %-13s %s\n",
                    "S-ID", "FIRST NAME", "LAST NAME", "BIRTH DATE", "FEES");
            for (Student student : spc.getStudents()) {
                System.out.println("\t" + student);
            }
        }
    }

    public static void trainersPerCourse(List<TrainersPerCourse> trainersPerCourse) {
        for (TrainersPerCourse tpc : trainersPerCourse) {
            System.out.println("");
            System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                    "C-ID", "TITLE", "STREAM", "TYPE", "DURATION");
            System.out.println(tpc.getCourse());
            System.out.println("--------------------------------------------------------------");
            System.out.printf("\t%-5s %-13s %-17s %s\n",
                    "T-ID", "FIRST NAME", "LAST NAME", "SUBJECT");
            for (Trainer trainer : tpc.getTrainers()) {
                System.out.println("\t" + trainer);
            }
        }
    }

    public static void assignmentsPerCourse(List<AssignmentsPerCourse> assignmentsPerCourse) {
        for (AssignmentsPerCourse apc : assignmentsPerCourse) {
            System.out.println("");
            System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                    "C-ID", "TITLE", "STREAM", "TYPE", "DURATION");
            System.out.println(apc.getCourse());
            System.out.println("--------------------------------------------------------------");
            for (Assignment assignment : apc.getAssigmnents()) {
                System.out.println(assignment);
            }
        }
    }
}
