package menus;

import entities.Trainer;
import entities.User;
import entitiesDao.TrainerDao;
import java.util.Scanner;
import joinedEntities.StudentsPerCourse;
import joinedEntitiesDao.AssignmentPerStudentPerCourseDao;
import joinedEntitiesDao.StudentsPerCourseDao;
import joinedEntitiesDao.TrainersPerCourseDao;
import xfunctions.Print;
import xfunctions.Reload;

public class TrainerMenu {

    public static void options(Scanner sc, User user) {
        TrainerDao sd = new TrainerDao();
        TrainersPerCourseDao tpcDao = new TrainersPerCourseDao();
        Trainer trainer = sd.readTrainerbyUid(user.getuId());
        AssignmentPerStudentPerCourseDao asc = new AssignmentPerStudentPerCourseDao();
        String choice;
        do {
            System.out.println("");
            System.out.println("----Trainer MENU-----");
            System.out.println("Hello " + trainer.getFirstName());
            System.out.println("Press:  '1' to View Your Courses");
            System.out.println("        '2' to View Students per Course");
            System.out.println("        '3' to View Assignments per Student");
            System.out.println("        '4' to Mark Assignments");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    Print.coursesPerTrainer(trainer.gettId());
                    break;
                case "2":
                    Print.trainerStudents(trainer.gettId(), sc);
                    break;
                case "3":
                    System.out.println("");
                    Print.assignmentsPerStudentPerCourse(trainer.gettId(), sc);
                    break;
                case "4":
                    System.out.println("");
                    asc.markAssignments(trainer.gettId(), sc);
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
