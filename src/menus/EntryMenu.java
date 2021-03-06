package menus;

import entities.User;
import java.util.Scanner;

public class EntryMenu {

    public static User options(Scanner sc, User user) {
        String role = user.getRole();
        String choice;
        if (role.equals("head master")) {
            System.out.println("Hello Head Master");
            do {
                System.out.println("----SCHOOL DATA MENU-----");
                System.out.println("Press:  '1' to Enter Courses Menu");
                System.out.println("        '2' to Enter Students Menu");
                System.out.println("        '3' to Enter Assignments Menu");
                System.out.println("        '4' to Enter Trainers Menu");
                System.out.println("        'X' to exit");
                System.out.print("Your Option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        CoursesMenu.options(sc, user);
                        break;
                    case "2":
                        StudentMenu.options(sc, user);
                        break;
                    case "3":
                        AssignmentsMenu.options(sc, user);
                        break;
                    case "4":
                        TrainerMenu.options(sc, user);
                        break;
                    case "x":
                    case "X":
                        break;
                    default:
                        System.out.println("Invalid Option. Try Again");
                        continue;
                }
            } while (!(choice.equals("x") || choice.equals("X")));
        }
        return null;
    }
}
