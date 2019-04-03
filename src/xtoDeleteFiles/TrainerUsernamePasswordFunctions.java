package xtoDeleteFiles;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xtoDeleteFiles.TrainerUsernamePassword;

public class TrainerUsernamePasswordFunctions {

    public static TrainerUsernamePassword newTrainerUsernamePassword(Scanner sc) {

        TrainerUsernamePassword tup = new TrainerUsernamePassword();
////        sc.nextLine();
//        System.out.print("Enter username: ");
//        sup.setUsername(sc.nextLine());
//        System.out.print("Enter password: ");
//        sup.setPassword(sc.nextLine());
//        return sup;
//    }
//
//    public static TrainerUsernamePassword
//            addToTrainerUsernamePasswordTable(MyDatabase db, PreparedStatement pst,
//                    TrainerUsernamePassword tup, String query, int id) {
//
//        try {
//            pst.setString(1, tup.getUsername());
//            pst.setString(2, tup.getPassword());
//            pst.setInt(3, id);
//            int result = pst.executeUpdate();
//            if (result > 0) {
//                System.out.println("Success");
//            } else {
//                System.out.println("Failed");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TrainerUsernamePasswordFunctions.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return tup;
    }
}
