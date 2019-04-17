package menus;

import entities.Student;
import entitiesDao.StudentDao;
import entitiesFunctions.StudentFunctions;
import java.util.List;
import java.util.Scanner;
import xfunctions.Print;

public class HeadMasterStudentMenu {

    public static void options(Scanner sc) {
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
            System.out.println("        '2' to Edit a Student");
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
                    System.out.println("Student Deleted");
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
