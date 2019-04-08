package entitiesFunctions;

import entities.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xtoDeleteFiles.UsernamePassword;
import functions.Password;

public class UserFunctions {

    public static User newUser(Scanner sc) {
        User user = new User();
//        sc.nextLine();
        System.out.print("Enter username: ");
        user.setUsername(sc.nextLine());
        System.out.print("Enter password: ");
        user.setPassword(Password.encrypt(sc.nextLine()));
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
}
