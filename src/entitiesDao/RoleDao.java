package entitiesDao;

import entities.Role;
import static entitiesDao.GenericDao.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class RoleDao extends GenericDao {

    public String readRoleById(int id) {
        String query = "SELECT r_id, role, "
                + "         FROM roles";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Role role = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            role = new Role(rs.getInt("r_id"), rs.getString("role"));
        } catch (SQLException ex) {
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return role.getRole();
    }

    public int readIdByRole(String role) {
        int id = 0;
        String query = "SELECT r_id "
                + "     FROM roles"
                + "     WHERE role = '" + role + "';";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                id = rs.getInt("r_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return id;
    }

    public List<Role> readRoleList() {
        List<Role> list = new ArrayList();
        String query = "SELECT * "
                + "     FROM roles;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Role role = new Role(rs.getInt("r_id"),
                        rs.getString("role"));
                list.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
