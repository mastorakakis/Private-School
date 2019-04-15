package entitiesFunctions;

import entities.CourseObjective;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class CourseObjectiveFunctions {

    public static CourseObjective newCourseObjective(Scanner sc) {
        CourseObjective courseObjective = new CourseObjective();
//        sc.nextLine();
        System.out.print("Enter new objective title: ");
        courseObjective.setTitle(sc.nextLine());
        return courseObjective;
    }

    public static CourseObjective addToCourseObjectiveTable(MyDatabase db,
            PreparedStatement pst, CourseObjective courseObjective,
            String query, int id) {
        try {
            pst.setString(1, courseObjective.getTitle());
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
            Logger.getLogger(CourseObjectiveFunctions.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return courseObjective;
    }
}
