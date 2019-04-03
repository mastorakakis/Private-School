package entitiesFunctions;

import entities.Trainer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class TrainerFunctions {

    //creates a trainer instance
    public static Trainer newTrainer(Scanner sc) {
        Trainer trainer = new Trainer();
        sc.nextLine();
        System.out.print("Enter Trainer's First Name: ");
        trainer.setFirstName(sc.nextLine());
        System.out.print("Enter Trainer's Last Name: ");
        trainer.setLastName(sc.nextLine());
        System.out.print("Enter Trainer's Subject: ");
        trainer.setSubject(sc.nextLine());
        return trainer;
    }

    //to insert id=zero & to update id:='number'
    public static Trainer addToTrainerTable(MyDatabase db, PreparedStatement pst,
            Trainer trainer, String query, int id) {
        try {
            pst.setString(1, trainer.getFirstName());
            pst.setString(2, trainer.getLastName());
            pst.setString(3, trainer.getSubject());
            if (id != 0) {
                pst.setInt(4, id);
            }
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerFunctions.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return trainer;
    }
}
