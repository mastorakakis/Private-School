package menus;

import entities.Course;
import entitiesDao.CourseDao;
import entitiesFunctions.CourseFunctions;
import java.util.List;
import java.util.Scanner;
import xfunctions.Print;

public class HeadMasterCourseMenu {

    public static void options(Scanner sc) {
        CourseDao cd = new CourseDao();
        Course course = new Course();
        List<Course> courses = null;
        String choice;
        int cId;
        do {
            System.out.println("");
            System.out.println("----COURSE MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to View All Courses");
            System.out.println("        '2' to Edit a Course");
            System.out.println("        '3' to Add a New Course");
            System.out.println("        '4' to Delete a Course");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            int flag = 0;
            switch (choice) {
                case "1":
                    System.out.println("");
                    courses = cd.readCourseList();
                    Print.courses(courses);
                    break;
                case "2":
                    System.out.println("");
                    cId = CourseFunctions.checkImportId(cd, courses, sc);
                    course = cd.updateByCourseId(cId, sc);
                    System.out.println(course.getTitle() + " Updated");
                    break;
                case "3":
                    System.out.println("");
                    course = cd.createNewCourse(sc);
                    System.out.println("New Course: " + course.getTitle());
                    break;
                case "4":
                    System.out.println("");
                    cId = CourseFunctions.checkImportId(cd, courses, sc);
                    cd.deleteByCourseId(cId);
                    System.out.println("Course Deleted");
                    break;
                case "x":
                case "X":
                    continue;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
            System.out.print("Press enter to continue...");
            if (flag == 0) {
                sc.nextLine();
            }
            sc.nextLine();
        } while (!(choice.equals("x") || choice.equals("X")));
    }
}
