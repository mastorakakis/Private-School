package views;

import entities.Person;
import entitiesDao.CourseDao;
import java.util.Scanner;
import prints.Print;

public class Courses {

    public static void menu(Scanner sc, Person user) {
        String role = user.getRole();
        String choice;
        if (role.equals("Head Master")) {
            do {
                System.out.println("----COURSES MENU-----");
                System.out.println("Press:  '1' to View All Courses");
                System.out.println("        '2' to View All Students per Course");
                System.out.println("        '3' to View All Trainers per Course");
                System.out.println("        '4' to View All Assignments per Course");
                System.out.println("        '5' to View the Schedule per Course");
                System.out.println("        'X' to exit");
                System.out.print("Your Option: ");
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        CourseDao cd = new CourseDao();
                        Print.course(cd.readCourseList());
                        System.out.print("Press enter to continue...");
                        sc.nextLine();
                        break;
                    case "2":
                    case "3":
                    case "4":
                    case "x":
                    case "X":
                        break;
                    default:
                        System.out.println("Invalid Option. Try Again");
                        continue;
                }
            } while (!(choice.equals("x") || choice.equals("X")));
        }
    }
}
