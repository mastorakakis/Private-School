package menus;

import entities.Course;
import entities.JavaScheduleDate;
import entities.Student;
import entities.User;
import entitiesDao.CourseDao;
import entitiesDao.JavaScheduleDateDao;
import entitiesDao.StudentDao;
import functions.Print;
import functions.PrintSchedule;
import functions.Reload;
import java.util.List;
import java.util.Scanner;
import joinedEntitiesDao.StudentsPerCourseDao;

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
            System.out.println("        '2' to View Assignment Submission Date");
            System.out.println("        '3' to Sumbint Assignment");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    PrintSchedule.student(student.getStId(), sc);
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

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
