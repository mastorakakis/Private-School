package menus;

import entities.User;
import entitiesDao.StudentDao;
import entitiesDao.TrainerDao;
import java.util.Scanner;
import methods.Print;

public class TrainersMenu {

    public static void options(Scanner sc, User user) {
        TrainerDao td = new TrainerDao();
        String role = user.getRole();
        String choice;
        int id;
        if (role.equals("head master") || role.equals("trainer")) {
            do {
                System.out.println("");
                System.out.println("----TRAINERS MENU-----");
                System.out.println("Press:  '1' to View  All Trainers");
                System.out.println("        '2' to Create  a Trainer");
                System.out.println("        '3' to Edit    a Trainer");
                System.out.println("        '4' to Delete  a Trainer");
                System.out.println("        'X' to exit");
                System.out.print("Your option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        Print.trainer(td.readTrainerList());
                        System.out.print("Press enter to continue...");
                        sc.nextLine();
                        sc.nextLine();
                        break;
                    case "2":
                        td.createNewTrainer(sc);
                        break;
                    case "3":
                        Print.trainer(td.readTrainerList());
                        System.out.print("Choose a trainer: ");
                        id = sc.nextInt();
                        td.updateByTrainerId(id, sc);
                        break;
                    case "4":
                        Print.trainer(td.readTrainerList());
                        System.out.print("Choose a trainer: ");
                        id = sc.nextInt();
                        td.deleteByTrainerId(id);
                    case "x":
                    case "X":
                        break;
                    default:
                        System.out.println("Invalid Option. Try Again");
                        continue;
                }
            } while (!(choice.equals("x") || choice.equals("X")));
        }
    }
}
