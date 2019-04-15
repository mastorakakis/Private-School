package entitiesDao;

import entities.CourseScheduleDate;
import static entitiesDao.GenericDao.URL;
import entitiesFunctions.CourseScheduleDateFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class CourseScheduleDateDao extends GenericDao {

//    public List<CourseScheduleDate> readCourseScheduleDateList() {
//        List<CourseScheduleDate> list = new ArrayList();
//        String query = "SELECT * FROM private_school.java_schedule_dates;";
//        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
//        ResultSet rs = db.MyResultSet();
//        try {
//            while (rs.next()) {
//                CourseScheduleDate javaScheduleDate
//                        = new CourseScheduleDate(rs.getInt("c_id"),
//                                rs.getInt("j_ob_id"), rs.getDate("c_date"));
//                list.add(javaScheduleDate);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseScheduleDateDao.class.getName()).log(Level.SEVERE,
//                    null, ex);
//        } finally {
//            db.closeConnections();
//        }
//        return list;
//    }
    public List<CourseScheduleDate> readCourseScheduleDateListByCId(int cId) {
        List<CourseScheduleDate> list = new ArrayList();
        String stream = new CourseDao().readByCourseId(cId).getStream();
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "SELECT * FROM " + stream + "_schedule_dates "
                + "     WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        CourseScheduleDate courseScheduleDate = null;
        ResultSet rs = db.MyResultSet(cId);
        try {
            while (rs.next()) {
                courseScheduleDate = new CourseScheduleDate(rs.getInt("c_id"),
                        rs.getInt(2), rs.getDate("c_date"));
                list.add(courseScheduleDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseScheduleDateDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public CourseScheduleDate createNewCourseScheduleDate(Scanner sc, String stream) {
        CourseScheduleDate courseScheduleDate
                = CourseScheduleDateFunctions.newCourseScheduleDate(sc);
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "INSERT INTO " + stream + "_schedule_dates "
                + "     VALUES (DEFAULT, ?, ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        CourseScheduleDateFunctions.addToCourseScheduleDateTable(db, pst,
                courseScheduleDate, query, 0);
        db.closeConnections();
        return courseScheduleDate;
    }

    public CourseScheduleDate updateByCourseScheduleDateId(int id, Scanner sc,
            String stream) {
        CourseScheduleDate courseScheduleDate
                = CourseScheduleDateFunctions.newCourseScheduleDate(sc);
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "UPDATE " + stream + "_schedule_dates "
                + "     SET c_0b_id = ?, c_date = ? "
                + "     WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        CourseScheduleDateFunctions.addToCourseScheduleDateTable(db, pst,
                courseScheduleDate, query, id);
        db.closeConnections();
        return courseScheduleDate;
    }

    public void deleteByCourseScheduleDateId(int cId) {
        String stream = new CourseDao().readByCourseId(cId).getStream();
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "DELETE FROM " + stream + "_schedule_dates WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(cId);
        db.closeConnections();
    }
}
