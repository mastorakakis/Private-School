package menus;

import entities.User;
import entitiesDao.TrainerDao;
import java.util.Scanner;
import functions.Print;
import joinedEntitiesDao.TrainersPerCourseDao;

public class TrainerMenu {

    public static void options(Scanner sc, User user) {
        TrainerDao td = new TrainerDao();
        TrainersPerCourseDao tpc = new TrainersPerCourseDao();
        String role = user.getRole();
        String choice;
        int id;
        if (role.equals("head master") || role.equals("trainer")) {
            do {
                System.out.println("");
                System.out.println("----TRAINERS MENU-----");
                System.out.println("Press:  '1' to View  All Trainers");
                System.out.println("Press:  '2' to View  All Trainers per Course");
                System.out.println("        '3' to Create  a Trainer");
                System.out.println("        '4' to Edit    a Trainer");
                System.out.println("        '5' to Delete  a Trainer");
                System.out.println("        'X' to exit");
                System.out.print("Your option: ");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        Print.trainers(td.readTrainerList());
                        break;
                    case "2":
                        Print.trainersPerCourse(tpc.readTrainerPerCourseList());

                        break;
                    case "3":
                        td.createNewTrainer(sc);
                        break;
                    case "4":
                        Print.trainers(td.readTrainerList());
                        System.out.print("Choose a trainer: ");
                        id = sc.nextInt();
                        td.updateByTrainerId(id, sc);
                        break;
                    case "5":
                        Print.trainers(td.readTrainerList());
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
                System.out.print("Press enter to continue...");
                sc.nextLine();
                sc.nextLine();
            } while (!(choice.equals("x") || choice.equals("X")));
        }
    }
}
