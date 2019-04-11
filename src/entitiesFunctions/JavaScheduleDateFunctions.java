package entitiesFunctions;

import entities.Course;
import entities.JavaScheduleDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class JavaScheduleDateFunctions {

    public static JavaScheduleDate newJavaScheduleDate(Scanner sc) {
        JavaScheduleDate javaScheduleDate = new JavaScheduleDate();
        sc.nextLine();
        System.out.print("Enter Java Objective id: ");
        javaScheduleDate.setJobId(sc.nextInt());
        int flag = 0;
        do {
            DateTimeFormatter formatter;
            formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            javaScheduleDate.setCDate(null);
            try {
                System.out.print("Enter date (dd/mm/yyyy): ");
//                javaScheduleDate.setCDate(LocalDate.parse(sc.next(), formatter));
//                if (javaScheduleDate.getStartDate().isAfter(javaScheduleDate.getEndDate())) {
//                    System.out.println("End Date Must be Later Than Start "
//                            + "Date.");
//                    continue;
//                }
                flag = 1;
            } catch (Exception e) {
                System.out.println("Invalid Date Input.");
            }
        } while (flag == 0);
        return javaScheduleDate;
    }

    public static JavaScheduleDate addToJavaScheduleDateTable(MyDatabase db,
            PreparedStatement pst, JavaScheduleDate javaScheduleDate,
            String query, int id) {
        try {
            pst.setInt(1, javaScheduleDate.getJobId());
            pst.setDate(2, Date.valueOf(javaScheduleDate.getCDate()),
                    Calendar.getInstance());
            if (id != 0) {
                pst.setInt(3, id);
            }
            int noumero = pst.executeUpdate();
            if (noumero > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JavaScheduleDateFunctions.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return javaScheduleDate;
    }
}
