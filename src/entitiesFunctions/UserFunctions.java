package entitiesFunctions;

import entities.Role;
import entities.User;
import entitiesDao.RoleDao;
import entitiesDao.UserDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xfunctions.Password;

public class UserFunctions {

    public static User newUser(Scanner sc) {
        sc.nextLine();
        User user = new User();
        do {
            System.out.print("Enter username: ");
            user.setUsername(sc.nextLine());
            if (userListContains(user)) {
                System.out.println("Username Already exists");
            }
        } while (userListContains(user));
        System.out.print("Enter password (no spaces): ");
        user.setPassword(Password.encrypt(sc.next()));
        return user;
    }

//to insert id=zero & to update id:='number'
    public static User addToUserTable(MyDatabase db, PreparedStatement pst,
            User user, String query, int id) {
        try {
            if (id != 0) {
                pst.setString(1, user.getUsername());
                pst.setString(2, user.getPassword());
                pst.setInt(3, id);
            }
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static boolean userListContains(User user) {
        UserDao ud = new UserDao();
        List<User> users = ud.readUserList();
        for (User u : users) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
