package menus;

import entities.Person;
import entitiesDao.StudentDao;
import java.util.Scanner;
import prints.Print;

public class StudentsMenu {

    public static void options(Scanner sc, Person user) {
        StudentDao sd = new StudentDao();
        String role = user.getRole();
        String choice;
        int id;
        if (role.equals("head master") || role.equals("student")) {
            do {
                System.out.println("");
                System.out.println("----STUDENTS MENU-----");
                System.out.println("Press:  '1' to View All Students");
                System.out.println("        '2' to Create a Student");
                System.out.println("        '3' to Edit a Student");
                System.out.println("        '4' to Delete a Student");
                System.out.println("        'X' to exit");
                System.out.print("Your option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        Print.student(sd.readStudentList());
                        System.out.print("Press enter to continue...");
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case "2":
                        sd.createNewStudent(sc);
                        break;
                    case "3":
                        Print.student(sd.readStudentList());
                        System.out.print("Choose a student: ");
                        id = sc.nextInt();
                        sd.updateByStudentId(id, sc);
                        break;
                    case "4":
                        Print.student(sd.readStudentList());
                        System.out.print("Choose a student: ");
                        id = sc.nextInt();
                        sd.deleteByStudentId(id);
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
