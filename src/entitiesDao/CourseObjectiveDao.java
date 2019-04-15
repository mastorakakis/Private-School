package entitiesDao;

import entities.CourseObjective;
import static entitiesDao.GenericDao.URL;
import entitiesFunctions.CourseObjectiveFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class CourseObjectiveDao extends GenericDao {

    public List<CourseObjective> readCourseObjectiveList(String stream) {
        List<CourseObjective> list = new ArrayList();
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "SELECT  * FROM " + stream + "_objectives;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();

        try {
            while (rs.next()) {
                CourseObjective javaObjective = new CourseObjective(rs.getInt("c_ob_id"),
                        rs.getString("title"));
                list.add(javaObjective);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseObjectiveDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public CourseObjective readByCourseObjectiveId(int id, String stream) {
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "SELECT * FROM " + stream + "_objectives WHERE c_ob_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        CourseObjective courseObjective = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            courseObjective = new CourseObjective(rs.getInt("c_ob_id"),
                    rs.getString("title"));
        } catch (SQLException ex) {
            Logger.getLogger(CourseObjectiveDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return courseObjective;
    }

    public CourseObjective createNewCourseObjective(Scanner sc, String stream) {
        if (stream.equals("c#")) {
            stream = "c";
        }
        CourseObjective courseObjective = CourseObjectiveFunctions.newCourseObjective(sc);
        String query = "INSERT INTO " + stream + "_objectives "
                + "            (c_ob_id)"
                + "     VALUES (?)";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        CourseObjectiveFunctions.addToCourseObjectiveTable(db, pst, courseObjective,
                query, 0);
        db.closeConnections();
        return courseObjective;
    }

    public CourseObjective updateByCourseObjectiveId(int id, Scanner sc, String stream) {
        if (stream.equals("c#")) {
            stream = "c";
        }
        CourseObjective courseObjective = CourseObjectiveFunctions.newCourseObjective(sc);
        String query = "UPDATE " + stream + "_objectives "
                + "     SET title = ? "
                + "     WHERE c_ob_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        CourseObjectiveFunctions.addToCourseObjectiveTable(db, pst, courseObjective,
                query, id);
        db.closeConnections();
        return courseObjective;
    }

    public void deleteByCourseObjectiveId(int id, String stream) {
        if (stream.equals("c#")) {
            stream = "c";
        }
        String query = "DELETE FROM " + stream + "_objectives "
                + "       WHERE c_ob_id = ?";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }
}
