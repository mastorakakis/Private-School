package joinedEntitiesDao;

import entitiesDao.GenericDao;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntities.AssignmentPerStudentPerCourse;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class AssignmentPerStudentPerCourseDao extends GenericDao {

    public List<AssignmentPerStudentPerCourse> readAssignmentsPerStudentPerCourseList() {
        List<AssignmentPerStudentPerCourse> list = new ArrayList();
        String query = "SELECT * "
                + "     FROM private_school.assignments_students_course;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                AssignmentPerStudentPerCourse asc
                        = new AssignmentPerStudentPerCourse(rs.getInt("c_id"),
                                rs.getInt("a_id"), rs.getInt("st_id"),
                                rs.getInt("oral_mark"), rs.getInt("total_mark"),
                                rs.getBoolean("submitted"));
                list.add(asc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerStudentPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<AssignmentPerStudentPerCourse> readAssignmentsPerStudentPerCourseByStIdList(int id) {
        List<AssignmentPerStudentPerCourse> list = new ArrayList();
        String query = "SELECT * FROM private_school.assignments_students_course "
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        AssignmentPerStudentPerCourse asc = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                asc = new AssignmentPerStudentPerCourse(rs.getInt("c_id"),
                        rs.getInt("a_id"), rs.getInt("st_id"),
                        rs.getInt("oral_mark"), rs.getInt("total_mark"),
                        rs.getBoolean("submitted"));
                list.add(asc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerStudentPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<AssignmentPerStudentPerCourse> readAssignmentsPerStudentPerCourseByCIdList(int id) {
        List<AssignmentPerStudentPerCourse> list = new ArrayList();
        String query = "SELECT * FROM private_school.assignments_students_course "
                + "     WHERE c_id = ?"
                + "     ORDER BY st_id;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        AssignmentPerStudentPerCourse asc = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                asc = new AssignmentPerStudentPerCourse(rs.getInt("c_id"),
                        rs.getInt("a_id"), rs.getInt("st_id"),
                        rs.getInt("oral_mark"), rs.getInt("total_mark"),
                        rs.getBoolean("submitted"));
                list.add(asc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerStudentPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public void updateByAssignmentsPerStudentPerCourseId(int stId, int aId) {
        String query = "UPDATE `assignments_students_course` "
                + "     SET submitted = TRUE "
                + "     WHERE st_id = " + stId + " AND a_id = " + aId;
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        try {
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerStudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeConnections();
    }

    public List<AssignmentPerStudentPerCourse> readUnsubmittedAssignmentListByTId(int tId, Scanner sc) {
        List<AssignmentPerStudentPerCourse> list = readAssignmentsPerStudentPerCourseByCIdList(tId);
        String query = "SELECT  c_id, assignments_students_course.a_id, st_id, "
                + "             oral_mark, total_mark, submitted "
                + "     FROM assignments_trainers_course "
                + "         INNER JOIN assignments_students_course USING (c_id) "
                + "     WHERE t_id = ? AND submitted = 0";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(tId);
        AssignmentPerStudentPerCourse asc = null;
        try {
            while (rs.next()) {
                asc = new AssignmentPerStudentPerCourse(rs.getInt("c_id"),
                        rs.getInt("a_id"), rs.getInt("st_id"),
                        rs.getInt("oral_mark"), rs.getInt("total_mark"),
                        rs.getBoolean("submitted"));
                list.add(asc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerStudentPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public void markAssignments(int tId, Scanner sc) {
        List<AssignmentPerStudentPerCourse> list
                = Print.assignmentsPerStudentPerCourse(tId, sc);
        boolean contains = false;
        boolean exit = false;
        String stId = "0";
        String aId = "0";
        int oralMark = 0;
        int totalMark = 0;
        do {
            try {
                System.out.println("");
                System.out.print("Choose Student ID and sumbitted Assignment ID separated by space\n"
                        + "Type zero zero (0 0) to exit\n"
                        + "Your choice (StudentID   AssignmentID): ");
                stId = sc.next();
                aId = sc.next();
                for (AssignmentPerStudentPerCourse asc : list) {
                    if (asc.getStId() == parseInt(stId)
                            && asc.getaId() == parseInt(aId)
                            && asc.isSubmitted() == true) {
                        contains = true;
                        System.out.print("Set Oral Mark: ");
                        oralMark = parseInt(sc.next());
                        System.out.print("Set Total Mark: ");
                        totalMark = parseInt(sc.next());
                        contains = true;
                        break;
                    }
                }
                if (parseInt(stId) == 0 && parseInt(aId) == 0) {
                    exit = true;
                }
                if (!contains && !exit) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                stId = "0";
                aId = "0";
                oralMark = 0;
                totalMark = 0;
                contains = false;
            }
        } while (!contains && !exit);
        if (!exit) {
            String query = "UPDATE `assignments_students_course` "
                    + "     SET oral_mark = " + oralMark
                    + "       , total_mark = " + totalMark
                    + "     WHERE st_id = " + parseInt(stId)
                    + "     AND a_id = " + parseInt(aId);
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            PreparedStatement pst = db.MyPreparedStatement();
            try {
                int result = pst.executeUpdate();
                if (result > 0) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentPerStudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.closeConnections();
        }
    }
}
