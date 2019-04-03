package menus;

import entities.Entity;
import entitiesDao.GenericDao;
import entitiesDao.StudentDao;
import entitiesDao.TrainerDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;
import xtoDeleteFiles.StudentUsernamePassword;
import usernamePassword.UsernamePassword;
import usernamePasswordDao.StudentUsernamePasswordDao;
import usernamePasswordDao.TrainerUsernamePasswordDao;
import usernamePasswordFunctions.Password;

public class Login extends GenericDao {

    public static Entity prompt(Scanner sc) {
        System.out.print("Username: ");
        String loginUsername = sc.next();
        System.out.print("Password: ");
        String loginPassword = Password.encrypt(sc.next());

        List<UsernamePassword> list = new ArrayList();
        list = new StudentUsernamePasswordDao().readStudentUsernamePasswordList();
        list.addAll(new TrainerUsernamePasswordDao().readTrainerUsernamePasswordList());
        for (UsernamePassword u : list) {
            if (u.getUsername().equals(loginUsername) && u.getPassword().equals(loginPassword)) {
                System.out.println(u.getRole());
                if (u.getRole().equals("student")) {
                    return new StudentDao().readByStudentId(u.getId());
                } else {
                    return new TrainerDao().readByTrainerId(u.getId());
                }
            }
        }
        return null;
    }
}
