package menus;

import entities.User;
import java.util.Scanner;
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
            System.out.println("        '3' to Enter Course Menu");
            System.out.println("        '4' to Enter Assignment Menu");
            System.out.println("        '5' to Edit Menu");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    HeadMasterStudentMenu.options(sc);
                    continue;
                case "2":
                    HeadMasterTrainerMenu.options(sc);
                    continue;
                case "3":
                    System.out.println("");
                    HeadMasterCourseMenu.options(sc);
                    continue;
                case "4":
                    System.out.println("");
                    HeadMasterAssignmentMenu.options(sc);
                    continue;
                case "5":
                    System.out.println("");
                    HeadMasterEditMenu.options(sc);
                    continue;
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
