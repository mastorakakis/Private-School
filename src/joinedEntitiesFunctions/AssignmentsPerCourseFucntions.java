package joinedEntitiesFunctions;

import entities.Assignment;
import entities.Course;
import entities.Student;
import entitiesDao.AssignmentDao;
import entitiesDao.CourseDao;
import entitiesDao.StudentDao;
import static java.lang.Integer.parseInt;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import joinedEntities.AssignmentsPerStudentPerCourse;
import joinedEntitiesDao.AssignmentsPerStudentPerCourseDao;
import static joinedEntitiesFunctions.StudentsPerCourseFunctions.studentFromStIdList;

public class AssignmentsPerCourseFucntions {

    public static void submitAssignment(int stId, Scanner sc) {
        AssignmentsPerStudentPerCourseDao ascDao = new AssignmentsPerStudentPerCourseDao();
        List<AssignmentsPerStudentPerCourse> ascList
                = ascDao.readAssignmentsPerStudentPerCourseByStIdList(stId);
        System.out.printf("%-5s %-25s %-17s %s\n", "ID", "TITLE", "SUBMISSION DATE", "SUBMITTED");
        System.out.println("--------------------------------------------------------------");
        for (AssignmentsPerStudentPerCourse asc : ascList) {
            Course c = new CourseDao().readByCourseId(asc.getcId());
            Student st = new StudentDao().readByStudentId(asc.getStId());
            Assignment a = new AssignmentDao().readByAssignmentId(asc.getaId());
            System.out.printf("%-5d %-25s %-17s %s\n", asc.getaId(),
                    a.getTitle(), a.getSubmissionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    asc.isSubmitted() ? "YES" : "NO");
        }
        int aId = 0;
        do {
            try {
                System.out.print("Select Assignment id to sumbit: ");
                aId = parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } while (aId == 0);
        ascDao.updateByAssignmentsPerStudentPerCourseId(stId, aId);
    }

    public static Assignment assignmentFromAIdList(List<Integer> aIds, Scanner sc) {
        int id = 0;
        do {
            if (id < 0) {
                System.out.print("Enter ID: ");
            } else {
                System.out.print("Select Assignment by ID: ");
            }
            while (!sc.hasNextInt()) {
                System.out.print("Invalid Input. Enter ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (!aIds.contains(id)) {
                System.out.print("Invalid Input. ");
            }
        } while (!aIds.contains(id));
        return new AssignmentDao().readByAssignmentId(id);
    }

    public static Assignment getAssignment(Scanner sc) {
        List<Integer> aIds = new ArrayList();
        List<Assignment> assignments = new AssignmentDao().readAssignmentList();
        for (Assignment assignment : assignments) {
            aIds.add(assignment.getaId());
        }
        Assignment assignment = assignmentFromAIdList(aIds, sc);
        return assignment;
    }
}
