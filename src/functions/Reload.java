package functions;

import java.util.Scanner;

public class Reload {

    public static String menu(String choice, Scanner sc) {
        while (!choice.equals("reload menu")) {
            System.out.print("Are you sure you want to exit (Y/N): ");
            String answer = sc.next();
            if (answer.equals("y") || answer.equals("Y")) {
                break;
            } else if (answer.equals("n") || answer.equals("N")) {
                choice = "reload menu";
            } else {
                System.out.println("Invalid option");
            }
        }
        return choice;
    }
}
