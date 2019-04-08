package menus;

import entities.User;
import entitiesDao.CourseDao;
import java.util.Scanner;
import functions.Print;

public class CoursesMenu {

    public static void options(Scanner sc, User user) {
        CourseDao cd = new CourseDao();
        String role = user.getRole();
        String choice;
        int id;
        if (role.equals("head master")) {
            do {
                System.out.println("");
                System.out.println("----COURSES MENU-----");
                System.out.println("Press:  '1' to View All Courses");
                System.out.println("        '2' to Create a Course");
                System.out.println("        '3' to Edit   a Course");
                System.out.println("        '4' to Delete a Course");
                System.out.println("        'X' to exit");
                System.out.print("Your option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        Print.courses(cd.readCourseList());
                        System.out.print("Press enter to continue...");
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case "2":
                        cd.createNewCourse(sc);
                        break;
                    case "3":
                        Print.courses(cd.readCourseList());
                        System.out.print("Choose a course: ");
                        id = sc.nextInt();
                        cd.updateByCourseId(id, sc);
                        break;
                    case "4":
                        Print.courses(cd.readCourseList());
                        System.out.print("Choose a course: ");
                        id = sc.nextInt();
                        cd.deleteByCourseId(id);
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
