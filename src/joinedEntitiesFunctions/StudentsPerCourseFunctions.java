package joinedEntitiesFunctions;

import entities.Course;
import entities.Student;
import entitiesDao.CourseDao;
import entitiesDao.StudentDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentsPerCourseFunctions {

    public static Student studentFromStIdList(List<Integer> stIds, Scanner sc) {
        int id = 0;
        do {
            if (id < 0) {
                System.out.print("Enter ID: ");
            } else {
                System.out.print("Select Student by ID: ");
            }
            while (!sc.hasNextInt()) {
                System.out.print("Invalid Input. Enter ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (!stIds.contains(id)) {
                System.out.print("Invalid Input. ");
            }
        } while (!stIds.contains(id));
        return new StudentDao().readByStudentId(id);
    }

    public static Course courseFromCIdList(List<Integer> cIds, Scanner sc) {
        int id = 0;
        do {
            if (id < 0) {
                System.out.print("Enter ID: ");
            } else {
                System.out.print("Select Course by ID: ");
            }
            while (!sc.hasNextInt()) {
                System.out.print("Invalid Input. Enter ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (!cIds.contains(id)) {
                System.out.print("Invalid Input. ");
            }
        } while (!cIds.contains(id));
        return new CourseDao().readByCourseId(id);
    }

    public static Student getStudent(Scanner sc) {
        List<Integer> stIds = new ArrayList();
        List<Student> students = new StudentDao().readStudentList();
        for (Student student : students) {
            stIds.add(student.getStId());
        }
        Student student = studentFromStIdList(stIds, sc);
        return student;
    }
}
