package joinedEntitiesFunctions;

import entities.Assignment;
import entities.Course;
import entities.Student;
import entitiesDao.AssignmentDao;
import entitiesDao.CourseDao;
import entitiesDao.StudentDao;
import static java.lang.Integer.parseInt;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import joinedEntities.AssignmentPerStudentPerCourse;
import joinedEntitiesDao.AssignmentPerStudentPerCourseDao;

public class AssignmentsPerCourseFucntions {

    public static void submitAssignment(int stId, Scanner sc) {
        AssignmentPerStudentPerCourseDao ascDao = new AssignmentPerStudentPerCourseDao();
        List<AssignmentPerStudentPerCourse> ascList
                = ascDao.readAssignmentsPerStudentPerCourseByStIdList(stId);
        System.out.printf("%-5s %-25s %-17s %s\n", "ID", "TITLE", "SUBMISSION DATE", "SUBMITTED");
        System.out.println("--------------------------------------------------------------");
        for (AssignmentPerStudentPerCourse asc : ascList) {
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
                System.out.print("Choose Assignment id to sumbit: ");
                aId = parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } while (aId == 0);
        ascDao.updateByAssignmentsPerStudentPerCourseId(stId, aId);

    }
}
