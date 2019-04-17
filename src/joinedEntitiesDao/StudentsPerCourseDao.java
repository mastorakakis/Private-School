package joinedEntitiesDao;

import entitiesDao.GenericDao;
import entities.Course;
import entities.Student;
import entitiesDao.CourseDao;
import entitiesDao.StudentDao;
import entitiesFunctions.StudentFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntities.StudentsPerCourse;
import joinedEntitiesFunctions.StudentsPerCourseFunctions;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class StudentsPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    StudentDao sd = new StudentDao();

    public List<StudentsPerCourse> readStudentsPerCourseList() {
        String query = "SELECT * FROM private_school.students_course;";
        List<StudentsPerCourse> spcList = new ArrayList();
        List<Course> courses = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                if (!courses.contains(cd.readByCourseId(rs.getInt("c_id")))) {
                    courses.add(cd.readByCourseId(rs.getInt("c_id")));
                }
            }
            for (Course course : courses) {
                List<Student> students = new ArrayList();
                rs.beforeFirst();
                while (rs.next()) {
                    if (course.getcId() == rs.getInt("c_id")) {
                        students.add(sd.readByStudentId(rs.getInt("st_id")));
                    }
                }
                spcList.add(new StudentsPerCourse(course, students));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return spcList;
    }

    public List<Course> readCoursesPerStudentByStId(int id) {
        List<Course> courses = new ArrayList();
        CourseDao cd = new CourseDao();
        String query = "SELECT c_id FROM students_course "
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                courses.add(cd.readByCourseId(rs.getInt("c_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return courses;
    }

    public List<Student> readStudentsPerCourseByCIdList(int cId) {
        String query = "SELECT * FROM private_school.students_course"
                + "         INNER JOIN students USING (st_id) "
                + "     WHERE c_id = ?;";
        List<Student> list = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(cId);
        try {
            while (rs.next()) {
                Student student = new Student(rs.getInt("st_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getDate("date_of_birth"), rs.getInt("tuition_fees"));
                list.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public int addToStudentPerCourse(Scanner sc) {
        int result = 0;
        Print.coursesPerStudent();
        Student student = StudentsPerCourseFunctions.getStudent(sc);
        Course course = StudentsPerCourseFunctions.getCourse(sc);
        List<Student> students
                = readStudentsPerCourseByCIdList(course.getcId());
        if (students.contains(student)) {
            System.out.println("Student is already in that course");
            return result;
        }
        String query = "INSERT INTO students_course "
                + "VALUES (" + student.getStId() + ", " + course.getcId() + ");";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        try {
            result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        addToAssignmentPerStudentPerCourse(student, course);
        return result;

// add stId to tables
    }

    public void addToAssignmentPerStudentPerCourse(Student student, Course course) {
        AssignmentsPerCourseDao apcDao = new AssignmentsPerCourseDao();
        List<Integer> aIds
                = new AssignmentsPerCourseDao().readAssignmentIdsPerCourseByCIdList(course.getcId());
        for (Integer aId : aIds) {
            String query = "INSERT INTO assignments_students_course "
                    + "                 (c_id, a_id, st_id) "
                    + "     VALUES      (" + course.getcId() + ", " + aId + ", "
                    + student.getStId() + ");";
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            PreparedStatement pst = db.MyPreparedStatement();
            int result;
            try {
                result = pst.executeUpdate();
                if (result > 0) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnections();
            }
        }
    }
}
