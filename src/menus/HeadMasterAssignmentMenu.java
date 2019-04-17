package menus;

import entities.Assignment;
import entities.Student;
import entitiesDao.AssignmentDao;
import entitiesDao.StudentDao;
import entitiesFunctions.AssignmentFunctions;
import entitiesFunctions.StudentFunctions;
import java.util.List;
import java.util.Scanner;
import xfunctions.Print;

public class HeadMasterAssignmentMenu {

    public static void options(Scanner sc) {
        AssignmentDao ad = new AssignmentDao();
        Assignment assignment = new Assignment();
        List<Assignment> assignments = null;
        String choice;
        int cId;
        do {
            System.out.println("");
            System.out.println("----ASSIGNMENT MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to View All Assignments");
            System.out.println("        '2' to Edit a Assignment");
            System.out.println("        '3' to Add a New Assignment");
            System.out.println("        '4' to Delete a Assignment");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    assignments = ad.readAssignmentList();
                    Print.assignments(assignments);
                    break;
                case "2":
                    System.out.println("");
                    cId = AssignmentFunctions.checkImportId(ad, assignments, sc);
                    assignment = ad.updateByAssignmentId(cId, sc);
                    System.out.println(assignment.getTitle() + " Updated");
                    break;
                case "3":
                    System.out.println("");
                    assignment = ad.createNewAssignment(sc);
                    System.out.println("New Assignment: " + assignment.getTitle());
                    break;
                case "4":
                    System.out.println("");
                    cId = AssignmentFunctions.checkImportId(ad, assignments, sc);
                    ad.deleteByAssignmentId(cId);
                    System.out.println("Assignment Deleted");
                    break;
                case "x":
                case "X":
                    continue;
                default:
                    System.out.println("Invalid Option");
                    continue;
            }
            System.out.print("Press enter to continue...");
            sc.nextLine();
            sc.nextLine();
        } while (!(choice.equals("x") || choice.equals("X")));
    }
}
