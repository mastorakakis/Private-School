package entitiesFunctions;

import entities.Assignment;
import entities.Student;
import entitiesDao.AssignmentDao;
import entitiesDao.StudentDao;
import static java.lang.Integer.parseInt;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class AssignmentFunctions {

    //creates an assignment instance
    public static Assignment newAssignment(Scanner sc) {
        Assignment assignment = new Assignment();
        sc.nextLine();
        System.out.print("Enter Assignment Title: ");
        assignment.setTitle(sc.nextLine());
        System.out.print("Enter Assignment Description: ");
        assignment.setDescription(sc.nextLine());
//        sc.nextLine();
        do {
            if ((assignment.getOralMark() < 10)
                    || (assignment.getOralMark() > 50)) {
                System.out.print("Invalid Input. Enter a Positive"
                        + " Number (10-50): ");
            } else {
                System.out.print("Enter Assignment Oral Mark "
                        + "(10-50): ");
            }
            while (!sc.hasNextInt()) {
                System.out.print("Invalid Input. "
                        + "Enter a Positive Number (10-50): ");
                sc.next();
            }
            assignment.setOralMark(sc.nextInt());
        } while ((assignment.getOralMark() < 10)
                || (assignment.getOralMark() > 50));
        int flag = 0;
        do {
            DateTimeFormatter formatter;
            formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            assignment.setSubmissionDate(null);
            try {
                System.out.print("Enter Submission Date (dd/mm/yyyy): ");
                assignment.setSubmissionDate(LocalDate.parse(sc.next(),
                        formatter));
                flag = 1;
            } catch (Exception e) {
                System.out.println("Invalid Date Input.");
            }
        } while (flag == 0);
        return assignment;
    }

    //to insert id=zero & to update id:='number'
    public static Assignment addToAssignmentTable(MyDatabase db,
            PreparedStatement pst, Assignment assignment, String query, int id) {
        try {
            pst.setString(1, assignment.getTitle());
            pst.setString(2, assignment.getDescription());
            pst.setInt(3, assignment.getOralMark());
            pst.setInt(4, assignment.getTotalMark());
            pst.setDate(5, Date.valueOf(assignment.getSubmissionDate()),
                    Calendar.getInstance());
            if (id != 0) {
                pst.setInt(6, id);
            }
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentFunctions.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return assignment;
    }

    public static int checkImportId(AssignmentDao ad, List<Assignment> assignments, Scanner sc) {
        List<Integer> ids = new ArrayList();
        assignments = ad.readAssignmentList();
        Print.assignments(assignments);
        for (Assignment assignment : assignments) {
            ids.add(assignment.getaId());
        }
        String aId;
        sc.nextLine();
        do {
            try {
                System.out.print("Select a Assignment by ID: ");
                aId = sc.next();
                if (!ids.contains(parseInt(aId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                aId = "0";
            }
        } while (!ids.contains(parseInt(aId)));
        return parseInt(aId);
    }
}
