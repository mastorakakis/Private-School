package entitiesFunctions;

import entities.Student;
import entitiesDao.StudentDao;
import static java.lang.Integer.parseInt;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class StudentFunctions {

    //creates a student instance
    public static Student newStudent(Scanner sc) {
        int fees = 0;
        Student student = new Student();
        do {
            sc.nextLine();
            System.out.print("Enter Student's First Name: ");
            student.setFirstName(sc.nextLine());
            System.out.print("Enter Student's Last Name: ");
            student.setLastName(sc.nextLine());
            DateTimeFormatter formatter;
            formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            student.setDateOfBirth(null);
            try {
                System.out.print("Enter Birth Date (dd/mm/yyyy): ");
                student.setDateOfBirth(LocalDate.parse(sc.next(), formatter));
                if (studentListContains(student)) {
                    System.out.println("Student Already exists");
                }
            } catch (Exception e) {
                System.out.println("Invalid Date Input.");
            }
        } while (studentListContains(student));
        do {
            if (fees < 0) {
                System.out.print("Invalid Input. Enter a Positive Number: ");
            } else {
                System.out.print("Enter Student's Tuition Fees: ");
            }
            while (!sc.hasNextInt()) {
                System.out.print("Invalid Input. "
                        + "Enter a Positive Number: ");
                sc.next();
            }
            fees = sc.nextInt();
        } while (fees < 0);
        student.setTuitionFees(fees);
        student.setRole("student");
        return student;
    }

    //to insert id=zero & to update id:='number'
    public static Student addToStudentTable(MyDatabase db, PreparedStatement pst,
            Student student, int id) {
        try {
            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            pst.setDate(3, Date.valueOf(student.getDateOfBirth()));
//            pst.setDate(3, Date.valueOf(student.getDateOfBirth()),
//                    Calendar.getInstance());
            pst.setInt(4, student.getTuitionFees());
            if (id != 0) {
                pst.setInt(5, id);
            }
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFunctions.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return student;
    }

    public static boolean studentListContains(Student student) {
        StudentDao sd = new StudentDao();
        List<Student> students = sd.readStudentList();
        for (Student s : students) {
            if (s.equals(student)) {
                return true;
            }
        }
        return false;
    }

    public static int checkImportId(StudentDao sd, List<Student> students, Scanner sc) {
        List<Integer> ids = new ArrayList();
        students = sd.readStudentList();
        Print.students(students);
        for (Student student : students) {
            ids.add(student.getStId());
        }
        String stId;
        sc.nextLine();
        do {
            try {
                System.out.print("Select a Student by ID: ");
                stId = sc.next();
                if (!ids.contains(parseInt(stId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                stId = "0";
            }
        } while (!ids.contains(parseInt(stId)));
        return parseInt(stId);
    }
}
