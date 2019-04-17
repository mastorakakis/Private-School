package menus;

import entities.Student;
import entities.User;
import entitiesDao.StudentDao;
import java.util.List;
import java.util.Scanner;
import joinedEntities.StudentsPerCourse;
import joinedEntitiesDao.StudentsPerCourseDao;
import xfunctions.Reload;

public class HeadMasterEditMenu {

    public static void options(Scanner sc) {
        StudentDao sd = new StudentDao();
        StudentsPerCourseDao spc = new StudentsPerCourseDao();
        List<Student> students;
        String choice;
        do {
            System.out.println("");
            System.out.println("----Head Master MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to Add a Student to a Course");
            System.out.println("        '2' to Remove a Student from a Course");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    spc.addToStudentPerCourse(sc);
                    continue;
                case "2":

                    continue;
                case "3":
                    System.out.println("");

                    continue;
                case "4":
                    System.out.println("");

                    continue;
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
