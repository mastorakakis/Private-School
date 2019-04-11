package entitiesFunctions;

import entities.JavaObjective;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class JavaObjectiveFunctions {

    public static JavaObjective newJavaObjective(Scanner sc) {
        int fees = 0;
        JavaObjective javaObjective = new JavaObjective();
//        sc.nextLine();
        System.out.print("Enter new objective title: ");
        javaObjective.setTitle(sc.nextLine());
        return javaObjective;
    }

    public static JavaObjective addToJavaObjectiveTable(MyDatabase db,
            PreparedStatement pst, JavaObjective javaObjective,
            String query, int id) {
        try {
            pst.setString(1, javaObjective.getTitle());
            if (id != 0) {
                pst.setInt(2, id);
            }
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JavaObjectiveFunctions.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return javaObjective;
    }
}
