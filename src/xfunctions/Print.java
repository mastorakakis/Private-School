package xfunctions;

import entities.Assignment;
import entities.Course;
import entities.CourseObjective;
import entities.CourseScheduleDate;
import entities.Student;
import entities.Trainer;
import entitiesDao.AssignmentDao;
import entitiesDao.CourseDao;
import entitiesDao.CourseScheduleDateDao;
import entitiesDao.StudentDao;
import entitiesDao.TrainerDao;
import static java.lang.Integer.parseInt;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import joinedEntities.AssignmentsPerStudentPerCourse;
import joinedEntities.AssignmentsPerCourse;
import joinedEntities.StudentsPerCourse;
import joinedEntities.TrainersPerCourse;
import joinedEntitiesDao.AssignmentsPerStudentPerCourseDao;
import joinedEntitiesDao.AssignmentsPerCourseDao;
import joinedEntitiesDao.StudentsPerCourseDao;
import joinedEntitiesDao.TrainersPerCourseDao;

public class Print {

    public static void courses(List<Course> list) {
        System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                "ID", "TITLE", "STREAM", "TYPE", "DURATION");
        for (Course course : list) {
            System.out.println(course);
        }
    }

    public static void students(List<Student> list) {
        System.out.printf("%-5s %-13s %-17s %-13s %s\n",
                "ID", "FIRST NAME", "LAST NAME", "BIRTH DATE", "FEES");
        for (Student student : list) {
            System.out.println(student);
        }
    }

    public static void trainers(List<Trainer> list) {
        System.out.printf("%-5s %-13s %-17s %s\n",
                "ID", "FIRST NAME", "LAST NAME", "SUBJECT");
        for (Trainer trainer : list) {
            System.out.println(trainer);
        }
    }

    public static void assignments(List<Assignment> list) {
        for (Assignment assignment : list) {
            System.out.println(assignment);
        }
    }

    public static void studentsPerCourse(List<StudentsPerCourse> studentsPerCourse) {
        for (StudentsPerCourse spc : studentsPerCourse) {
            System.out.println("");
            System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                    "C-ID", "TITLE", "STREAM", "TYPE", "DURATION");
            System.out.println(spc.getCourse());
            System.out.println("--------------------------------------------------------------");
            System.out.printf("\t%-5s %-13s %-17s %-13s %s\n",
                    "S-ID", "FIRST NAME", "LAST NAME", "BIRTH DATE", "FEES");
            for (Student student : spc.getStudents()) {
                System.out.println("\t" + student);
            }
        }
    }

    public static void coursesPerStudent() {
        StudentDao sd = new StudentDao();
        StudentsPerCourseDao spcDao = new StudentsPerCourseDao();
        List<StudentsPerCourse> studentsPerCourse = spcDao.readStudentsPerCourseList();
        List<Student> allStudents = sd.readStudentList();
        for (Student student : allStudents) {
            System.out.println(student);
            System.out.printf("%-5s %s", " ", "Courses: ");
            for (StudentsPerCourse spc : studentsPerCourse) {
                if (spc.getStudents().contains(student)) {
                    Course c = spc.getCourse();
                    System.out.printf("(%d. %s - %s - %s) ", c.getcId(),
                            c.getTitle(), c.getStream(), c.getType());
                }
            }
            System.out.println("");
        }
    }

    public static void coursesPerAssignment() {
        AssignmentDao ad = new AssignmentDao();
        AssignmentsPerCourseDao apcDao = new AssignmentsPerCourseDao();
        List<AssignmentsPerCourse> assignmentsPerCourse = apcDao.readAssignmentsPerCourseList();
        List<Assignment> allAssignments = ad.readAssignmentList();
        for (Assignment assignment : allAssignments) {
            System.out.print(assignment);
            System.out.printf("%-5s %s", " ", "Courses: ");
            for (AssignmentsPerCourse apc : assignmentsPerCourse) {
                if (apc.getAssignments().contains(assignment)) {
                    Course c = apc.getCourse();
                    System.out.printf("(%d. %s - %s - %s) ", c.getcId(),
                            c.getTitle(), c.getStream(), c.getType());
                }
            }
            System.out.println("");
        }
    }

    public static void coursesPerTrainer() {
        TrainerDao td = new TrainerDao();
        TrainersPerCourseDao tpcDao = new TrainersPerCourseDao();
        List<TrainersPerCourse> trainersPerCourse = tpcDao.readTrainersPerCourseList();
        List<Trainer> allTrainers = td.readTrainerList();
        for (Trainer trainer : allTrainers) {
            System.out.println(trainer);
            System.out.printf("%-5s %s", " ", "Courses: ");
            for (TrainersPerCourse tpc : trainersPerCourse) {
                if (tpc.getTrainers().contains(trainer)) {
                    Course c = tpc.getCourse();
                    System.out.printf("(%d. %s - %s - %s) ", c.getcId(),
                            c.getTitle(), c.getStream(), c.getType());
                }
            }
            System.out.println("");
        }
    }

    public static void trainersPerCourse(List<TrainersPerCourse> trainersPerCourse) {
        for (TrainersPerCourse tpc : trainersPerCourse) {
            System.out.println("");
            System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                    "C-ID", "TITLE", "STREAM", "TYPE", "DURATION");
            System.out.println(tpc.getCourse());
            System.out.println("--------------------------------------------------------------");
            System.out.printf("\t%-5s %-13s %-17s %s\n",
                    "T-ID", "FIRST NAME", "LAST NAME", "SUBJECT");
            for (Trainer trainer : tpc.getTrainers()) {
                System.out.println("\t" + trainer);
            }
        }
    }

    public static void assignmentsPerCourse(List<AssignmentsPerCourse> assignmentsPerCourse) {
        for (AssignmentsPerCourse apc : assignmentsPerCourse) {
            System.out.println("");
            System.out.printf("%-5s %-15s %-7s %-8s %s\n",
                    "C-ID", "TITLE", "STREAM", "TYPE", "DURATION");
            System.out.println(apc.getCourse());
            System.out.println("--------------------------------------------------------------");
            for (Assignment assignment : apc.getAssignments()) {
                System.out.println(assignment);
            }
        }
    }

    public static void courseObjectives(List<CourseObjective> list) {
        System.out.printf("%-4s %-15s", "ID", "TITLE");
        for (CourseObjective courseObjective : list) {
            System.out.println(courseObjective);
        }
    }

    public static void courseSchedule(List<CourseScheduleDate> list) {
        System.out.println("");
        System.out.printf("%-5s %-40s %s\n", "ID", "TITLE", "DATE");
        System.out.println("--------------------------------------------------------------");
        for (CourseScheduleDate courseScheduleDate : list) {
            System.out.println(courseScheduleDate);
        }
    }

    public static void submissionDates(int id) {
        List<AssignmentsPerStudentPerCourse> ascList
                = new AssignmentsPerStudentPerCourseDao().readAssignmentsPerStudentPerCourseByStIdList(id);
        System.out.printf("%-5s %-25s %-11s %-10s %-10s %s\n", "ID", "TITLE", "SUBMISSION", "ORAL MARK", "TOTAL MARK", "COURSE");
        System.out.println("---------------------------------------------------------------------");
        for (AssignmentsPerStudentPerCourse asc : ascList) {
            Course c = new CourseDao().readByCourseId(asc.getcId());
            Student st = new StudentDao().readByStudentId(asc.getStId());
            Assignment a = new AssignmentDao().readByAssignmentId(asc.getaId());
            System.out.printf("%-5s %-25s %-11s %-10s %-10s %s\n", asc.getaId(),
                    a.getTitle(),
                    asc.isSubmitted() ? "Submitted" : a.getSubmissionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    asc.getOralMark(), asc.getTotalMark(),
                    c.getTitle(), c.getStream(), c.getType());
        }
    }

    public static void ScheduleOfStudent(int stId, Scanner sc) {
        CourseScheduleDateDao csd = new CourseScheduleDateDao();
        StudentsPerCourseDao spc = new StudentsPerCourseDao();
        List<Course> list = spc.readCoursesPerStudentByStId(stId);
        Print.courses(list);
        List<Integer> ids = new ArrayList();
        for (Course course : list) {
            ids.add(course.getcId());
        }
        List<CourseScheduleDate> scheduleDates;
        String cId = "0";
        do {
            try {
                System.out.print("Select a course: ");
                cId = sc.next();
                if (!ids.contains(parseInt(cId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                cId = "0";
            }
        } while (!ids.contains(parseInt(cId)));
        scheduleDates = csd.readCourseScheduleDateListByCId(parseInt(cId));
        Print.courseSchedule(scheduleDates);
    }

    public static void trainerStudents(int tId, Scanner sc) {
        TrainersPerCourseDao tpcDao = new TrainersPerCourseDao();
        StudentsPerCourseDao spcDao = new StudentsPerCourseDao();
        CourseDao cDao = new CourseDao();
        List<Course> list = tpcDao.readCoursesPerTrainerByTId(tId);
        Print.courses(list);
        List<Integer> ids = new ArrayList();
        for (Course course : list) {
            ids.add(course.getcId());
        }
        String cId = "0";
        do {
            try {
                System.out.print("Select a course: ");
                cId = sc.next();
                if (!ids.contains(parseInt(cId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                cId = "0";
            }
        } while (!ids.contains(parseInt(cId)));
        Course course = cDao.readByCourseId(parseInt(cId));
        List<Student> students = spcDao.readStudentsPerCourseByCIdList(parseInt(cId));
        StudentsPerCourse spc = new StudentsPerCourse(course, students);
        List<StudentsPerCourse> spcList = new ArrayList();
        spcList.add(spc);
        studentsPerCourse(spcList);
    }

    public static void assignmentPerStudentPerCourseDetails(int stId) {
        AssignmentsPerStudentPerCourseDao ascDao = new AssignmentsPerStudentPerCourseDao();
        List<AssignmentsPerStudentPerCourse> list = ascDao.readAssignmentsPerStudentPerCourseByStIdList(stId);
        for (AssignmentsPerStudentPerCourse asc : list) {
            System.out.println(asc);
            System.out.println("");
        }
    }

    public static void coursesPerTrainer(int tId) {
        TrainersPerCourseDao tpcDao = new TrainersPerCourseDao();
        List<Course> list = tpcDao.readCoursesPerTrainerByTId(tId);
        courses(list);
    }

    public static List<AssignmentsPerStudentPerCourse> assignmentsPerStudentPerCourse(int tId, Scanner sc) {
        StudentDao sd = new StudentDao();
        AssignmentDao ad = new AssignmentDao();
        TrainersPerCourseDao tpcDao = new TrainersPerCourseDao();
        CourseDao cDao = new CourseDao();
        List<Course> list = tpcDao.readCoursesPerTrainerByTId(tId);
        Print.courses(list);
        List<Integer> ids = new ArrayList();
        for (Course course : list) {
            ids.add(course.getcId());
        }
        String cId = "0";
        do {
            try {
                System.out.print("Select a course: ");
                cId = sc.next();
                if (!ids.contains(parseInt(cId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                cId = "0";
            }
        } while (!ids.contains(parseInt(cId)));
        AssignmentsPerStudentPerCourseDao spcDao = new AssignmentsPerStudentPerCourseDao();
        List<AssignmentsPerStudentPerCourse> ascList
                = spcDao.readAssignmentsPerStudentPerCourseByCIdList(parseInt(cId));
        int stId = 0;
        for (AssignmentsPerStudentPerCourse asc : ascList) {
            if (stId != asc.getStId()) {
                System.out.println("");
                System.out.println(sd.readByStudentId(asc.getStId()));
                System.out.println("---------------------------------------------------------");
                stId = asc.getStId();
                System.out.printf("%-5s %-25s %-11s %-10s %-10s %s\n", "ID", "TITLE", "SUBMISSION", "ORAL MARK", "TOTAL MARK", "COURSE");
            }
            Assignment a = ad.readByAssignmentId(asc.getaId());
            Course c = cDao.readByCourseId(parseInt(cId));
            System.out.printf("%-5s %-25s %-11s %-10s %-10s %s\n", asc.getaId(),
                    a.getTitle(),
                    asc.isSubmitted() ? "Submitted" : a.getSubmissionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    asc.getOralMark(), asc.getTotalMark(),
                    c.getTitle(), c.getStream(), c.getType());
        }
        return ascList;
    }

}
