package menus;

import entities.Person;
import entitiesDao.AssignmentDao;
import entitiesDao.StudentDao;
import java.util.Scanner;
import prints.Print;

public class AssignmentsMenu {

    public static void options(Scanner sc, Person user) {
        AssignmentDao sd = new AssignmentDao();
        String role = user.getRole();
        String choice;
        int id;
        if (role.equals("head master") || role.equals("student")) {
            do {
                System.out.println("");
                System.out.println("----ASSIGNMENTS MENU-----");
                System.out.println("Press:  '1' to View All Assignments");
                System.out.println("        '2' to Create a Assignment");
                System.out.println("        '3' to Edit a Assignment");
                System.out.println("        '4' to Delete a Assignment");
                System.out.println("        'X' to exit");
                System.out.print("Your option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        Print.assignment(sd.readAssignmentList());
                        System.out.print("Press enter to continue...");
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case "2":
                        sd.createNewAssignment(sc);
                        break;
                    case "3":
                        Print.assignment(sd.readAssignmentList());
                        System.out.print("Choose a assignment: ");
                        id = sc.nextInt();
                        sd.updateByAssignmentId(id, sc);
                        break;
                    case "4":
                        Print.assignment(sd.readAssignmentList());
                        System.out.print("Choose a assignment: ");
                        id = sc.nextInt();
                        sd.deleteByAssignmentId(id);
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
