package joinedEntitiesDao;

import entitiesDao.GenericDao;
import joinedEntities.AssignmentsPerCourse;
import entities.Assignment;
import entities.Course;
import entitiesDao.AssignmentDao;
import entitiesDao.CourseDao;
import entitiesFunctions.CourseFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntitiesFunctions.AssignmentsPerCourseFucntions;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class AssignmentsPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    AssignmentDao ad = new AssignmentDao();

    public List<AssignmentsPerCourse> readAssignmentsPerCourseList() {
        String query = "SELECT * FROM private_school.assignments_course;";
        List<AssignmentsPerCourse> list = new ArrayList();
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
                List<Assignment> assignments = new ArrayList();
                rs.beforeFirst();
                while (rs.next()) {
                    if (course.getcId() == rs.getInt("c_id")) {
                        assignments.add(ad.readByAssignmentId(rs.getInt("a_id")));
                    }
                }
                list.add(new AssignmentsPerCourse(course, assignments));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<Integer> readAssignmentsIdsPerCourseByCIdList(int cId) {
        String query = "SELECT * FROM private_school.assignments_course"
                + "         INNER JOIN assignments USING (a_id) "
                + "     WHERE c_id = ?;";
        List<Integer> aIds = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(cId);
        try {
            while (rs.next()) {
                int aId = rs.getInt("a_id");
                aIds.add(aId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();

        }
        return aIds;
    }

    public int addToAssignmentsPerCourse(Scanner sc) {
        int result = 0;
        Print.coursesPerAssignment();
        Assignment assignment = AssignmentsPerCourseFucntions.getAssignment(sc);
        Course course = CourseFunctions.getCourse(sc);
        List<Assignment> assignments
                = readAssignmentsPerCourseByCIdList(course.getcId());
        if (assignments.contains(assignment)) {
            System.out.println("Assignment is already in that course");
            return result;
        }
        String query = "INSERT INTO assignments_course "
                + "VALUES (" + assignment.getaId() + ", " + course.getcId() + ");";
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
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        addToAssignmentsPerStudentPerCourse(assignment, course);
        addToAssignmentsPerTrainerPerCourse(assignment, course);
        return result;
    }

    public List<Assignment> readAssignmentsPerCourseByCIdList(int cId) {
        String query = "SELECT * FROM private_school.assignments_course"
                + "         INNER JOIN assignments USING (a_id) "
                + "     WHERE c_id = ?;";
        List<Assignment> list = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(cId);
        try {
            while (rs.next()) {
                Assignment assignment = new Assignment(rs.getInt("a_id"),
                        rs.getString("title"), rs.getString("t_description"),
                        rs.getInt("oral_mark"), rs.getInt("total_mark"),
                        rs.getDate("submission_date"));
                list.add(assignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public void addToAssignmentsPerStudentPerCourse(Assignment assignment, Course course) {
        List<Integer> stIds
                = new StudentsPerCourseDao().readStudentsIdsPerCourseByCIdList(course.getcId());
        for (Integer stId : stIds) {
            String query = "INSERT INTO assignments_students_course "
                    + "                 (c_id, st_id, a_id) "
                    + "     VALUES      (" + course.getcId() + ", " + stId + ", "
                    + assignment.getaId() + ");";
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            PreparedStatement pst = db.MyPreparedStatement();
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnections();
            }
        }
    }

    public void addToAssignmentsPerTrainerPerCourse(Assignment assignment, Course course) {
        List<Integer> tIds
                = new TrainersPerCourseDao().readTrainersIdsPerCourseByCIdList(course.getcId());
        for (Integer stId : tIds) {
            String query = "INSERT INTO assignments_trainers_course "
                    + "                 (c_id, t_id, a_id) "
                    + "     VALUES      (" + course.getcId() + ", " + stId + ", "
                    + assignment.getaId() + ");";
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            PreparedStatement pst = db.MyPreparedStatement();
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnections();
            }
        }
    }

    public int removeFromAssignmentsPerCourse(Scanner sc) {
        int result = 0;
        Print.coursesPerAssignment();
        Assignment assignment = AssignmentsPerCourseFucntions.getAssignment(sc);
        Course course = CourseFunctions.getCourse(sc);
        List<Assignment> assignments
                = readAssignmentsPerCourseByCIdList(course.getcId());
        if (!assignments.contains(assignment)) {
            System.out.println("Assignment is not in that course");
            return result;
        }
        String query = "DELETE FROM assignments_course "
                + "     WHERE a_id = " + assignment.getaId() + " AND c_id = "
                + course.getcId();
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
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        removeFromAssignmentsPerStudentPerCourse(assignment, course);
        removeFromAssignmentsPerTrainerPerCourse(assignment, course);
        return result;
    }

    public void removeFromAssignmentsPerStudentPerCourse(Assignment assignment, Course course) {
        String query = "Delete FROM assignments_students_course "
                + "     WHERE a_id = " + assignment.getaId()
                + "     AND c_id = " + course.getcId();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
    }

    public void removeFromAssignmentsPerTrainerPerCourse(Assignment assignment, Course course) {
        String query = "Delete FROM assignments_trainers_course "
                + "     WHERE a_id = " + assignment.getaId()
                + "     AND c_id = " + course.getcId();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
    }
}
