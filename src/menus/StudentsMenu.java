package menus;

import entities.User;
import entitiesDao.StudentDao;
import java.util.Scanner;
import functions.Print;
import joinedEntitiesDao.StudentsPerCourseDao;

public class StudentsMenu {

    public static void options(Scanner sc, User user) {
        StudentDao sd = new StudentDao();
        StudentsPerCourseDao spc = new StudentsPerCourseDao();
        String role = user.getRole();
        String choice;
        int id;
        if (role.equals("head master") || role.equals("student")) {
            do {
                System.out.println("");
                System.out.println("----STUDENTS MENU-----");
                System.out.println("Press:  '1' to View  All Students");

                System.out.println("        '2' to View  All Students per Course");

                System.out.println("        '3' to Create  a Student");
                System.out.println("        '4' to Edit    a Student");
                System.out.println("        '5' to Delete  a Student");
                System.out.println("        'X' to exit");
                System.out.print("Your option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        Print.students(sd.readStudentList());
                        break;
                    case "2":
                        Print.studentsPerCourse(spc.readStudentPerCourseList());
                        break;
                    case "3":
                        sd.createNewStudent(sc);
                        break;
                    case "4":
                        Print.students(sd.readStudentList());
                        System.out.print("Choose a student: ");
                        id = sc.nextInt();
                        sd.updateByStudentId(id, sc);
                        break;
                    case "5":
                        Print.students(sd.readStudentList());
                        System.out.print("Choose a student: ");
                        id = sc.nextInt();
                        sd.deleteByStudentId(id);
                    case "x":
                    case "X":
                        break;
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
}
