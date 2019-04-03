package entitiesDao;

import entities.Course;
import entitiesFunctions.CourseFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

// contains CRUD operations
public class CourseDao extends GenericDao {

    public List<Course> readCourseList() {
        List<Course> list = new ArrayList();
        String query = "SELECT c_id, "
                + "             title, "
                + "             stream_name, "
                + "             type_name, "
                + "             start_date, "
                + "             end_date "
                + "     FROM course "
                + "         NATURAL JOIN stream	"
                + "             NATURAL JOIN c_type "
                + "     ORDER BY c_id;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Course course = new Course(rs.getInt("c_id"),
                        rs.getString("title"), rs.getString("stream_name"),
                        rs.getString("type_name"),
                        rs.getDate("start_date"), rs.getDate("end_date"));
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public Course readByCourseId(int id) {
        String query = "SELECT c_id, "
                + "             title, "
                + "             stream_name, "
                + "             type_name, "
                + "             start_date, "
                + "             end_date "
                + "     FROM course "
                + "         NATURAL JOIN stream	"
                + "             NATURAL JOIN c_type "
                + "     WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Course course = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            course = new Course(rs.getInt("c_id"), rs.getString("title"),
                    rs.getString("stream_name"), rs.getString("type_name"),
                    rs.getDate("start_date"), rs.getDate("end_date"));
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return course;
    }

    public Course createNewCourse(Scanner sc) {
        Course course = CourseFunctions.newCourse(sc);
        String query = "INSERT INTO private_school.course (c_id, title, "
                + "                                        stream_id, type_id, "
                + "                                        start_date, "
                + "                                        end_date)"
                + "     VALUES (DEFAULT, ?, ?, ?, ?, ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        CourseFunctions.addToCourseTable(db, pst, course, query, 0);
        db.closeConnections();
        return course;
    }

    public Course updateByCourseId(int id, Scanner sc) {
        Course course = CourseFunctions.newCourse(sc);
        String query = "UPDATE course "
                + "     SET title = ?, stream_id = ?, type_id = ?, "
                + "     start_date = ?, end_date = ? "
                + "     WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        CourseFunctions.addToCourseTable(db, pst, course, query, id);
        db.closeConnections();
        return course;
    }

    public void deleteByCourseId(int id) {
        String query = "DELETE FROM course WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }
}
