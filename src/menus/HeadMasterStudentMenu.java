package menus;

import entities.Student;
import entities.User;
import entitiesDao.StudentDao;
import entitiesFunctions.StudentFunctions;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import joinedEntitiesFunctions.AssignmentsPerCourseFucntions;
import xfunctions.Print;
import xfunctions.Reload;

public class HeadMasterStudentMenu {

    public static void options(Scanner sc, User user) {
        StudentDao sd = new StudentDao();
        Student student = new Student();
        List<Student> students = null;
        String choice;
        int stId;
        do {
            System.out.println("");
            System.out.println("----STUDENT MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to View All Students");
            System.out.println("        '2' to Edit a student");
            System.out.println("        '3' to Add a New Student");
            System.out.println("        '4' to Delete a Student");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("");
                    students = sd.readStudentList();
                    Print.students(students);
                    break;
                case "2":
                    System.out.println("");
                    stId = StudentFunctions.checkImportId(sd, students, sc);
                    student = sd.updateByStudentId(stId, sc);
                    System.out.println(student.getFirstName() + " "
                            + student.getLastName() + " Updated");
                    break;
                case "3":
                    System.out.println("");
                    student = sd.createNewStudent(sc);
                    System.out.println("New Student: " + student.getFirstName()
                            + " " + student.getLastName());
                    break;
                case "4":
                    System.out.println("");
                    stId = StudentFunctions.checkImportId(sd, students, sc);
                    sd.deleteByStudentId(stId);
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
