package entitiesDao;

import entities.JavaScheduleDate;
import static entitiesDao.GenericDao.URL;
import entitiesFunctions.JavaScheduleDateFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class JavaScheduleDateDao extends GenericDao {

    public List<JavaScheduleDate> readJavaScheduleDateList() {
        List<JavaScheduleDate> list = new ArrayList();
        String query = "SELECT * FROM private_school.java_schedule_dates;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                JavaScheduleDate javaScheduleDate
                        = new JavaScheduleDate(rs.getInt("c_id"),
                                rs.getInt("j_ob_id"), rs.getDate("c_date"));
                list.add(javaScheduleDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JavaScheduleDateDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<JavaScheduleDate> readJavaScheduleDateListByCId(int id) {
        List<JavaScheduleDate> list = new ArrayList();
        String query = "SELECT * FROM private_school.java_schedule_dates "
                + "     WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        JavaScheduleDate javaScheduleDate = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                javaScheduleDate = new JavaScheduleDate(rs.getInt("c_id"),
                        rs.getInt("j_ob_id"), rs.getDate("c_date"));
                list.add(javaScheduleDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JavaScheduleDateDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public JavaScheduleDate createNewJavaScheduleDate(Scanner sc) {
        JavaScheduleDate javaScheduleDate
                = JavaScheduleDateFunctions.newJavaScheduleDate(sc);
        String query = "INSERT INTO private_school.java_schedule_dates "
                + "         (c_id, j_ob_id, c_date)"
                + "     VALUES (DEFAULT, ?, ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        JavaScheduleDateFunctions.addToJavaScheduleDateTable(db, pst,
                javaScheduleDate, query, 0);
        db.closeConnections();
        return javaScheduleDate;
    }

    public JavaScheduleDate updateByJavaScheduleDateId(int id, Scanner sc) {
        JavaScheduleDate javaScheduleDate
                = JavaScheduleDateFunctions.newJavaScheduleDate(sc);
        String query = "UPDATE private_school.java_schedule_dates "
                + "     SET j_0b_id = ?, c_date = ? "
                + "     WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        JavaScheduleDateFunctions.addToJavaScheduleDateTable(db, pst,
                javaScheduleDate, query, id);
        db.closeConnections();
        return javaScheduleDate;
    }

    public void deleteByjavaScheduleDateId(int id) {
        String query = "DELETE FROM java_schedule_dates WHERE c_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }
}
