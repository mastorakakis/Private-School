package menus;

import entities.Student;
import entities.Trainer;
import entities.User;
import entitiesDao.StudentDao;
import entitiesDao.TrainerDao;
import entitiesFunctions.StudentFunctions;
import entitiesFunctions.TrainerFunctions;
import java.util.List;
import java.util.Scanner;
import xfunctions.Print;
import xfunctions.Reload;

public class HeadMasterTrainerMenu {

    public static void options(Scanner sc) {
        TrainerDao td = new TrainerDao();
        Trainer trainer = new Trainer();
        List<Trainer> trainers = null;
        String choice;
        int tId;
        do {
            System.out.println("");
            System.out.println("----TRAINER MENU-----");
            System.out.println("Head Master");
            System.out.println("Press:  '1' to View All Trainers");
            System.out.println("        '2' to Edit a Trainer");
            System.out.println("        '3' to Add a New Trainer");
            System.out.println("        '4' to Delete a Trainer");
            System.out.println("        'X' to exit");
            System.out.print("Your option: ");
            choice = sc.next();
            int flag = 0;
            switch (choice) {
                case "1":
                    System.out.println("");
                    trainers = td.readTrainerList();
                    Print.trainers(trainers);
                    break;
                case "2":
                    System.out.println("");
                    tId = TrainerFunctions.checkImportId(td, trainers, sc);
                    trainer = td.updateByTrainerId(tId, sc);
                    System.out.println(trainer.getFirstName() + " "
                            + trainer.getLastName() + " Updated");
                    flag = 1;
                    break;
                case "3":
                    System.out.println("");
                    trainer = td.createNewTrainer(sc);
                    System.out.println("New Trainer: " + trainer.getFirstName()
                            + " " + trainer.getLastName());
                    flag = 1;
                    break;
                case "4":
                    System.out.println("");
                    tId = TrainerFunctions.checkImportId(td, trainers, sc);
                    td.deleteByTrainerId(tId);
                    System.out.println("Trainer Deleted");
                    break;
                case "x":
                case "X":
                    continue;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
            System.out.print("Press enter to continue...");
            if (flag == 0) {
                sc.nextLine();
            }
            sc.nextLine();
        } while (!(choice.equals("x") || choice.equals("X")));
    }
}
