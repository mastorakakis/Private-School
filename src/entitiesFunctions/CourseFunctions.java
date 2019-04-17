package entitiesFunctions;

import entities.Course;
import entities.Student;
import entitiesDao.CourseDao;
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

//creates a student instance
public class CourseFunctions {

    public static Course newCourse(Scanner sc) {
        Course course = new Course();
        sc.nextLine();
        System.out.print("Enter Course Title: ");
        course.setTitle(sc.nextLine());
        while (!(course.getStream().equals("java")
                || course.getStream().equals("c#"))) {
            System.out.print("Enter Course Stream (java/c#): ");
            course.setStream(sc.nextLine());
            if (!(course.getStream().equals("java")
                    || course.getStream().equals("c#"))) {
                System.out.print("Invalid Input. ");
            }
        }
        while (!(course.getType().equals("full")
                || course.getType().equals("part"))) {
            System.out.print("Enter Course Type (full/part): ");
            course.setType(sc.next());
            if (!(course.getType().equals("full")
                    || course.getType().equals("part"))) {
                System.out.print("Invalid Input. ");
            }
        }
        int flag = 0;
        do {
            DateTimeFormatter formatter;
            formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            course.setStartDate(null);
            course.setEndDate(null);
            try {
                System.out.print("Enter Start Date (dd/mm/yyyy): ");
                course.setStartDate(LocalDate.parse(sc.next(), formatter));
                System.out.print("Enter End Date (dd/mm/yyyy): ");
                course.setEndDate(LocalDate.parse(sc.next(), formatter));
                if (course.getStartDate().isAfter(course.getEndDate())) {
                    System.out.println("End Date Must be Later Than Start "
                            + "Date.");
                    continue;
                }
                flag = 1;
            } catch (Exception e) {
                System.out.println("Invalid Date Input.");
            }
        } while (flag == 0);
        return course;
    }

    //to insert id=zero & to update id:='number'
    public static Course addToCourseTable(MyDatabase db, PreparedStatement pst,
            Course course,
            String query, int id) {
        try {
            pst.setString(1, course.getTitle());
            if (course.getStream().equals("java")) {
                pst.setInt(2, 1);
            } else {
                pst.setInt(2, 2);
            }
            if (course.getType().equals("full")) {
                pst.setInt(3, 1);
            } else {
                pst.setInt(3, 2);
            }
            pst.setDate(4, Date.valueOf(course.getStartDate()),
                    Calendar.getInstance());
            pst.setDate(5, Date.valueOf(course.getEndDate()),
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
            Logger.getLogger(CourseFunctions.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return course;
    }

    public static int checkImportId(CourseDao cd, List<Course> courses, Scanner sc) {
        List<Integer> ids = new ArrayList();
        courses = cd.readCourseList();
        Print.courses(courses);
        for (Course course : courses) {
            ids.add(course.getcId());
        }
        String cId;
        sc.nextLine();
        do {
            try {
                System.out.print("Select a Course by ID: ");
                cId = sc.next();
                if (!ids.contains(parseInt(cId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                cId = "0";
            }
        } while (!ids.contains(parseInt(cId)));
        return parseInt(cId);
    }
}
