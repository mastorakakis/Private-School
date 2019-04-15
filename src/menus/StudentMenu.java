package menus;

import entities.Student;
import entities.User;
import entitiesDao.StudentDao;
import xfunctions.Print;
import xfunctions.Reload;
import java.util.Scanner;
import joinedEntitiesFunctions.AssignmentsPerCourseFucntions;

public class StudentMenu {

    public static void options(Scanner sc, User user) {
        StudentDao sd = new StudentDao();
        Student student = sd.readStudentbyUid(user.getuId());
        String choice;
        do {
            System.out.println("");
            System.out.println("----STUDENT MENU-----");
            System.out.println("Hello " + student.getFirstName());
            System.out.println("Press:  '1' to View Course Schedule");
            System.out.println("        '2' to View Assignment Details");
            System.out.println("        '3' to View Assignment Submission Date");
            System.out.println("        '4' to Sumbit Assignment");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    Print.ScheduleOfStudent(student.getStId(), sc);
                    break;
                case "2":
                    Print.assignmentPerStudentPerCourseDetails(student.getStId());
                    break;
                case "3":
                    System.out.println("");
                    Print.submissionDates(student.getStId());
                    break;
                case "4":
                    System.out.println("");
                    AssignmentsPerCourseFucntions.submitAssignment(student.getStId(), sc);
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
