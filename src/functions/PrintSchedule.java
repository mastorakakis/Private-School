package functions;

import entities.Course;
import entities.JavaScheduleDate;
import entitiesDao.JavaScheduleDateDao;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import joinedEntitiesDao.StudentsPerCourseDao;

public class PrintSchedule {

    public static void student(int stId, Scanner sc) {
        JavaScheduleDateDao jsd = new JavaScheduleDateDao();
        StudentsPerCourseDao spc = new StudentsPerCourseDao();
        List<Course> list
                = spc.readCoursesPerStudentByStId(stId);
        Print.courses(list);
        List<Integer> ids = new ArrayList();
        for (Course course : list) {
            ids.add(course.getcId());
        }
        List<JavaScheduleDate> schedule;
        String id = "0";
        do {
            try {
                System.out.print("Choose a course: ");
                id = sc.next();
                if (!ids.contains(parseInt(id))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                id = "0";
            }
        } while (!ids.contains(parseInt(id)));
        schedule = jsd.readJavaScheduleDateListByCId(parseInt(id));
        Print.javaSchedule(schedule);
    }
}
