package entitiesFunctions;

import entities.Student;
import entities.Trainer;
import entitiesDao.StudentDao;
import entitiesDao.TrainerDao;
import static entitiesFunctions.StudentFunctions.studentListContains;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class TrainerFunctions {

    //creates a trainer instance
    public static Trainer newTrainer(Scanner sc) {
        Trainer trainer = new Trainer();
        sc.nextLine();
        do {
            System.out.print("Enter Trainer's First Name: ");
            trainer.setFirstName(sc.nextLine());
            System.out.print("Enter Trainer's Last Name: ");
            trainer.setLastName(sc.nextLine());
            System.out.print("Enter Trainer's Subject: ");
            trainer.setSubject(sc.nextLine());
            if (trainerListContains(trainer)) {
                System.out.println("Trainer Already exists");
            }
        } while (trainerListContains(trainer));
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

    public static boolean trainerListContains(Trainer trainer) {
        TrainerDao sd = new TrainerDao();
        List<Trainer> trainers = sd.readTrainerList();
        for (Trainer t : trainers) {
            if (t.equals(trainer)) {
                return true;
            }
        }
        return false;
    }

    public static int checkImportId(TrainerDao sd, List<Trainer> trainers, Scanner sc) {
        List<Integer> ids = new ArrayList();
        trainers = sd.readTrainerList();
        Print.trainers(trainers);
        for (Trainer trainer : trainers) {
            ids.add(trainer.gettId());
        }
        String tId;
        sc.nextLine();
        do {
            try {
                System.out.print("Select a Trainer by ID: ");
                tId = sc.next();
                if (!ids.contains(parseInt(tId))) {
                    System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                tId = "0";
            }
        } while (!ids.contains(parseInt(tId)));
        return parseInt(tId);
    }
}
