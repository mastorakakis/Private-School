package menus;

import java.util.Scanner;
import joinedEntitiesDao.AssignmentsPerCourseDao;
import joinedEntitiesDao.StudentsPerCourseDao;
import joinedEntitiesDao.TrainersPerCourseDao;
import xfunctions.Print;
import xfunctions.Reload;

public class HeadMasterEditMenu {

    public static void options(Scanner sc) {
        StudentsPerCourseDao spc = new StudentsPerCourseDao();
        TrainersPerCourseDao tpc = new TrainersPerCourseDao();
        AssignmentsPerCourseDao apc = new AssignmentsPerCourseDao();
        String choice;
        do {
            System.out.println("");
            System.out.println("----Head Master MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to Add a Student to a Course");
            System.out.println("        '2' to Remove a Student from a Course");
            System.out.println("        '3' to Add a Trainer to a Course");
            System.out.println("        '4' to Remove a Trainer from a Course");
            System.out.println("        '5' to Add an Assignment to a Course");
            System.out.println("        '6' to Remove an Assignment from a Course");
            System.out.println("        '7' to View All Students per Course");
            System.out.println("        '8' to View All Trainers per Course");
            System.out.println("        '9' to View All Assignments per Course");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    spc.addToStudentsPerCourse(sc);
                    break;
                case "2":
                    System.out.println("");
                    spc.removeFromStudentsPerCourse(sc);
                    break;
                case "3":
                    System.out.println("");
                    tpc.addToTrainersPerCourse(sc);
                    break;
                case "4":
                    System.out.println("");
                    tpc.removeFromTrainersPerCourse(sc);
                    break;
                case "5":
                    System.out.println("");
                    apc.addToAssignmentsPerCourse(sc);
                    break;
                case "6":
                    System.out.println("");
                    apc.removeFromAssignmentsPerCourse(sc);
                    break;
                case "7":
                    System.out.println("");
                    Print.studentsPerCourse(spc.readStudentsPerCourseList());
                    break;
                case "8":
                    System.out.println("");
                    Print.trainersPerCourse(tpc.readTrainersPerCourseList());
                    break;
                case "9":
                    System.out.println("");
                    Print.assignmentsPerCourse(apc.readAssignmentsPerCourseList());
                    break;
                case "x":
                case "X":
                    break;
                default:
                    System.out.println("Invalid Option");
                    continue;
            }
            if (choice.equals("x") || choice.equals("X")) {
                choice = Reload.menu(choice, sc);
            } else {
                System.out.print("Press enter to continue...");
                sc.nextLine();
                sc.nextLine();
            }
        } while (!(choice.equals("x") || choice.equals("X")));
    }
}
