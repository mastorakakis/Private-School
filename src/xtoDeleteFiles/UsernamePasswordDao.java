package xtoDeleteFiles;

import entitiesDao.GenericDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class UsernamePasswordDao extends GenericDao {

//    public List<UsernamePassword> readUsernamePasswordList(String query) {
//        List<UsernamePassword> list = new ArrayList();
//        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
//        ResultSet rs = db.MyResultSet();
//        try {
//            while (rs.next()) {
//                UsernamePassword up
//                        = new UsernamePassword(rs.getInt(1),
//                                rs.getString("username"),
//                                rs.getString("password"),
//                                rs.getString("role"));
//                list.add(up);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UsernamePasswordDao.class.getName())
//                    .log(Level.SEVERE, null, ex);
//        } finally {
//            db.closeConnections();
//        }
//        return list;
//    }
//
//    public UsernamePassword readByUsernamePasswordId(int id, String query) {
//        UsernamePassword up = null;
//        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
//        ResultSet rs = db.MyResultSet(id);
//        try {
//            rs.next();
//            up = new UsernamePassword(rs.getInt(1),
//                    rs.getString(2),
//                    rs.getString(3),
//                    rs.getString(4));
//        } catch (SQLException ex) {
//            Logger.getLogger(UsernamePassword.class.getName())
//                    .log(Level.SEVERE, null, ex);
//        } finally {
//            db.closeConnections();
//        }
//        return up;
//    }
//
//    public UsernamePassword createNewUsernamePassword(Scanner sc, String query) {
//        UsernamePassword up
//                = UsernamePasswordFunctions.newUsernamePassword(sc);
//        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
//        PreparedStatement pst = db.MyPreparedStatement();
//        UsernamePasswordFunctions.addToUsernamePasswordTable(db,
//                pst, up, query, 0);
//        db.closeConnections();
//        return up;
//    }
//
//    public UsernamePassword updateByUsernamePasswordId(int id,
//            Scanner sc, String query) {
//        UsernamePassword up
//                = UsernamePasswordFunctions.newUsernamePassword(sc);
//        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
//        PreparedStatement pst = db.MyPreparedStatement();
//        UsernamePasswordFunctions.addToUsernamePasswordTable(db,
//                pst, up, query, id);
//        db.closeConnections();
//        return up;
//    }
}
