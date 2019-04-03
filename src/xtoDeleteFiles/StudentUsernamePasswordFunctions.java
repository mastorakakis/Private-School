package xtoDeleteFiles;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xtoDeleteFiles.StudentUsernamePassword;

public class StudentUsernamePasswordFunctions {

    //creates a StudentUsernamePassword instance
    public static StudentUsernamePassword newStudentUsernamePassword(Scanner sc) {

        StudentUsernamePassword sup = new StudentUsernamePassword();
////        sc.nextLine();
//        System.out.print("Enter username: ");
//        sup.setUsername(sc.nextLine());
//        System.out.print("Enter password: ");
//        sup.setPassword(sc.nextLine());
//        return sup;
//    }
//
//    public static StudentUsernamePassword
//            addToStudentUsernamePasswordTable(MyDatabase db, PreparedStatement pst,
//                    StudentUsernamePassword sup, String query, int id) {
//
//        try {
//            pst.setString(1, sup.getUsername());
//            pst.setString(2, sup.getPassword());
//            pst.setInt(3, id);
//            int result = pst.executeUpdate();
//            if (result > 0) {
//                System.out.println("Success");
//            } else {
//                System.out.println("Failed");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentUsernamePasswordFunctions.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return sup;
    }
}
