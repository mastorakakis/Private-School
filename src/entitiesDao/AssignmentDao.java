package entitiesDao;

import static entitiesDao.GenericDao.URL;
import entities.Assignment;
import entitiesFunctions.AssignmentFunctions;
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
public class AssignmentDao extends GenericDao {

    public List<Assignment> readAssignmentList() {
        List<Assignment> list = new ArrayList();
        String query = "SELECT a_id, title, t_description, oral_mark, "
                + "            total_mark, submission_date "
                + "     FROM assignments;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Assignment assignment = new Assignment(rs.getInt("a_id"),
                        rs.getString("title"), rs.getString("t_description"),
                        rs.getInt("oral_mark"), rs.getInt("total_mark"),
                        rs.getDate("submission_date"));
                list.add(assignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public Assignment readByAssignmentId(int id) {
        String query = "SELECT a_id, title, t_description, oral_mark, "
                + "            total_mark, submission_date "
                + "     FROM assignments "
                + "     WHERE a_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Assignment assignment = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            assignment = new Assignment(rs.getInt("a_id"),
                    rs.getString("title"), rs.getString("t_description"),
                    rs.getInt("oral_mark"), rs.getInt("total_mark"),
                    rs.getDate("submission_date"));
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return assignment;
    }

    public Assignment createNewAssignment(Scanner sc) {
        Assignment assignment = AssignmentFunctions.newAssignment(sc);
        String query = "INSERT INTO private_school.assignments "
                + "         (a_id, title, t_description, oral_mark, "
                + "             total_mark, submission_date)"
                + "     VALUES (DEFAULT, ?, ?, ?, ?, ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        AssignmentFunctions.addToAssignmentTable(db, pst, assignment, query, 0);
        db.closeConnections();
        return assignment;
    }

    public Assignment updateByAssignmentId(int id, Scanner sc) {
        Assignment assignment = AssignmentFunctions.newAssignment(sc);
        String query = "UPDATE assignments "
                + "     SET title = ?, t_description = ?, oral_mark = ?, "
                + "     total_mark = ?, submission_date = ? "
                + "     WHERE a_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        AssignmentFunctions.addToAssignmentTable(db, pst, assignment, query, id);
        db.closeConnections();
        return assignment;
    }

    public void deleteByAssignmentId(int id) {
        String query = "DELETE FROM assignments WHERE a_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }
}
