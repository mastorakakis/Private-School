package menus;

import entities.Student;
import entities.User;
import entitiesDao.StudentDao;
import java.util.Scanner;
import joinedEntitiesFunctions.AssignmentsPerCourseFucntions;
import xfunctions.Print;
import xfunctions.Reload;

public class HeadMasterMenu {

    public static void options(Scanner sc, User user) {

        String choice;
        do {
            System.out.println("");
            System.out.println("----Head Master MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to Enter Student Menu");
            System.out.println("        '2' to Enter Trainer Menu");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");

                    break;
                case "2":

                    break;
                case "3":
                    System.out.println("");

                    break;
                case "4":
                    System.out.println("");

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
