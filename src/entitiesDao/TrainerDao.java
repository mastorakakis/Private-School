package entitiesDao;

import entities.Trainer;
import entities.User;
import static entitiesDao.GenericDao.URL;
import entitiesFunctions.TrainerFunctions;
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
public class TrainerDao extends GenericDao {

    public List<Trainer> readTrainerList() {
        List<Trainer> list = new ArrayList();
        String query = "SELECT t_id, first_name, last_name, t_subject "
                + "     FROM trainers;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Trainer trainer = new Trainer(rs.getInt("t_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("t_subject"));
                list.add(trainer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public Trainer readByTrainerId(int id) {
        String query = "SELECT t_id, first_name, last_name, t_subject "
                + "     FROM trainers"
                + "     WHERE t_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Trainer trainer = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            trainer = new Trainer(rs.getInt("t_id"),
                    rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("t_subject"));
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return trainer;
    }

    public Trainer createNewTrainer(Scanner sc) {
        UserDao ud = new UserDao();
        User user = ud.createNewUser(sc, "trainer");
        Trainer trainer = TrainerFunctions.newTrainer(sc);
        String query = "INSERT INTO private_school.trainers "
                + "         (t_id, first_name, last_name, t_subject, u_id) "
                + "     VALUES (DEFAULT, ?, ?, ?, '" + ud.readMaxUid() + "');";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        TrainerFunctions.addToTrainerTable(db, pst, trainer, query, 0);
        db.closeConnections();
        return trainer;
    }

    public Trainer updateByTrainerId(int id, Scanner sc) {
        Trainer trainer = TrainerFunctions.newTrainer(sc);
        String query = "UPDATE trainers "
                + "     SET first_name = ?, last_name = ?, t_subject = ? "
                + "     WHERE t_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        TrainerFunctions.addToTrainerTable(db, pst, trainer, query, id);
        db.closeConnections();
        return trainer;
    }

    public void deleteByTrainerId(int id) {
        String query = "DELETE FROM users "
                + "       WHERE u_id = "
                + "         (SELECT u_id FROM trainers WHERE t_id = ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }

    public Trainer readTrainerbyUid(int id) {
        String query = "SELECT * FROM trainers "
                + "     WHERE u_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Trainer trainer = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            trainer = new Trainer(rs.getInt("t_id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("t_subject"));
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return trainer;
    }
}
