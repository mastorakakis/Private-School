package entitiesDao;

import entities.JavaObjective;
import entities.Student;
import entities.User;
import static entitiesDao.GenericDao.URL;
import entitiesFunctions.JavaObjectiveFunctions;
import entitiesFunctions.StudentFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class JavaObjectiveDao extends GenericDao {

    public List<JavaObjective> readJavaObjectiveList() {
        List<JavaObjective> list = new ArrayList();
        String query = "SELECT  * FROM java_objectives;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();

        try {
            while (rs.next()) {
                JavaObjective javaObjective = new JavaObjective(rs.getInt("j_ob_id"),
                        rs.getString("title"));
                list.add(javaObjective);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JavaObjectiveDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public JavaObjective readByJavaObjectiveId(int id) {
        String query = "SELECT * FROM java_objectives WHERE j_ob_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        JavaObjective javaObjective = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            javaObjective = new JavaObjective(rs.getInt("j_ob_id"),
                    rs.getString("title"));
        } catch (SQLException ex) {
            Logger.getLogger(JavaObjectiveDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return javaObjective;
    }

    public JavaObjective createNewStudent(Scanner sc) {
        JavaObjective javaObjective = JavaObjectiveFunctions.newJavaObjective(sc);
        String query = "INSERT INTO private_school.java_objectives "
                + "            (j_ob_id)"
                + "     VALUES (?)";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        JavaObjectiveFunctions.addToJavaObjectiveTable(db, pst, javaObjective,
                query, 0);
        db.closeConnections();
        return javaObjective;
    }

    public JavaObjective updateByJavaObjectiveId(int id, Scanner sc) {
        JavaObjective javaObjective = JavaObjectiveFunctions.newJavaObjective(sc);
        String query = "UPDATE java_objectives "
                + "     SET title = ? "
                + "     WHERE j_ob_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        JavaObjectiveFunctions.addToJavaObjectiveTable(db, pst, javaObjective,
                query, id);
        db.closeConnections();
        return javaObjective;
    }

    public void deleteByJavaObjectiveId(int id) {
        String query = "DELETE FROM java_objectives "
                + "       WHERE j_ob_id = ?";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }
}
