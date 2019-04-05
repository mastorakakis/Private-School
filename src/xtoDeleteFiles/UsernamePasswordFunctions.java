package xtoDeleteFiles;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class UsernamePasswordFunctions {

    //creates a StudentUsernamePassword instance
    public static UsernamePassword newUsernamePassword(Scanner sc) {

        UsernamePassword up = new UsernamePassword();
//        sc.nextLine();
        System.out.print("Enter username: ");
        up.setUsername(sc.nextLine());
        System.out.print("Enter password: ");
        up.setPassword(sc.nextLine());
        return up;
    }

    public static UsernamePassword
            addToUsernamePasswordTable(MyDatabase db, PreparedStatement pst,
                    UsernamePassword up, String query, int id) {

        try {
            pst.setString(1, up.getUsername());
            pst.setString(2, up.getPassword());
            pst.setInt(3, id);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsernamePasswordFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return up;
    }
}
