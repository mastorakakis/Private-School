package joinedEntitiesFunctions;

import entities.Trainer;
import entitiesDao.TrainerDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainersPerCourseFunctions {

    public static Trainer getTrainer(Scanner sc) {
        List<Integer> tIds = new ArrayList();
        List<Trainer> trainers = new TrainerDao().readTrainerList();
        for (Trainer trainer : trainers) {
            tIds.add(trainer.gettId());
        }
        Trainer trainer = trainerFromStIdList(tIds, sc);
        return trainer;
    }

    public static Trainer trainerFromStIdList(List<Integer> tIds, Scanner sc) {
        int id = 0;
        do {
            if (id < 0) {
                System.out.print("Enter ID: ");
            } else {
                System.out.print("Select Trainer by ID: ");
            }
            while (!sc.hasNextInt()) {
                System.out.print("Invalid Input. Enter ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (!tIds.contains(id)) {
                System.out.print("Invalid Input. ");
            }
        } while (!tIds.contains(id));
        return new TrainerDao().readByTrainerId(id);
    }
}
