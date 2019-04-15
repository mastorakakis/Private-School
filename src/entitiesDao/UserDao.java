package entitiesDao;

import entities.User;
import static entitiesDao.GenericDao.URL;
import entitiesFunctions.UserFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class UserDao extends GenericDao {

    public List<User> readUserList() {
        List<User> list = new ArrayList();
        String query = "SELECT u_id, username, password, role "
                + "     FROM users"
                + "         INNER JOIN roles USING (r_id)";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                User user = new User(rs.getInt("u_id"),
                        rs.getString("username"), rs.getString("password"),
                        rs.getString("role"));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public User readByUserId(int id) {
        String query = "SELECT u_id, username, password, role "
                + "     FROM users"
                + "         INNER JOIN roles USING (r_id)"
                + "     WHERE u_id = ?";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        User user = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            user = new User(rs.getInt("u_id"),
                    rs.getString("username"), rs.getString("password"),
                    rs.getString("role"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return user;
    }

    public User createNewUser(Scanner sc, String role) {
        User user = UserFunctions.newUser(sc);
        int rId = new RoleDao().readIdByRole(role);
        String query = "INSERT INTO users "
                + "         (u_id, username, password, r_id)"
                + "     VALUES (DEFAULT, '" + user.getUsername() + "', '"
                + user.getPassword() + "', " + rId + ");";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        UserFunctions.addToUserTable(db, pst, user, query, 0);
        db.closeConnections();
        return user;
    }

    public User updateByUserId(int id, Scanner sc) {
        User user = UserFunctions.newUser(sc);
        String query = "UPDATE users "
                + "     SET username = ?, password = ? "
                + "     WHERE u_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        UserFunctions.addToUserTable(db, pst, user, query, id);
        db.closeConnections();
        return user;
    }

    public void deleteByUserId(int id) {
        if (id != 1) {
            String query = "DELETE FROM user WHERE u_id = ?;";
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            db.MyPreparedStatement(id);
            db.closeConnections();
        } else {
            System.out.println("You cannot delete the head master");
        }
    }

    public int readMaxUid() {
        String query = "SELECT MAX(u_id) FROM users;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        int maxUid = 0;
        ResultSet rs = db.MyResultSet();
        try {
            rs.next();
            maxUid = rs.getInt("MAX(u_id)");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return maxUid;
    }

//    public void updateRoleByUserId(int id) {
//        String query = "UPDATE users "
//                + "     SET r_id = ? "
//                + "     WHERE u_id = ?;";
//        Connection conn = null;
//        PreparedStatement pst = null;
//        try {
//            conn = DriverManager.getConnection(URL, USERNAME, PASS);
//            pst = conn.prepareStatement(query);
//            pst.setInt(1, id);
//            int result = pst.executeUpdate();
//            if (result > 0) {
//                System.out.println("Success");
//            } else {
//                System.out.println("Id " + id + " was not found");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                pst.close();
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
}
