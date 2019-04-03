package menus;

import entities.HeadMaster;
import entities.Person;
import entities.Student;
import entities.Trainer;
import entitiesDao.GenericDao;
import entitiesDao.StudentDao;
import entitiesDao.TrainerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import usernamePassword.UsernamePassword;
import usernamePasswordDao.HeadMasterUsernamePasswordDao;
import usernamePasswordDao.StudentUsernamePasswordDao;
import usernamePasswordDao.TrainerUsernamePasswordDao;
import usernamePasswordFunctions.Password;

public class Login extends GenericDao {

    public static Person prompt(Scanner sc, String loginUsername) {

        System.out.print("Password: ");
//        String loginPassword = Password.encrypt(sc.next());
        String loginPassword = "96e89a298e0a9f469b9ae458d6afae9f";
        List<UsernamePassword> list = new ArrayList();
        list = new StudentUsernamePasswordDao().readStudentUsernamePasswordList();
        list.addAll(new TrainerUsernamePasswordDao().readTrainerUsernamePasswordList());
        list.addAll(new HeadMasterUsernamePasswordDao().readHeadUsernamePasswordList());
        for (UsernamePassword u : list) {
            if (u.getUsername().equals(loginUsername) && u.getPassword().equals(loginPassword)) {
                if (u.getRole().equals("student")) {
                    Student s = new StudentDao().readByStudentId(u.getId());
                    s.setRole("student");
                    return s;
                } else if (u.getRole().equals("trainer")) {
                    Trainer t = new TrainerDao().readByTrainerId(u.getId());
                    t.setRole("trainer");
                    return t;
                } else if (u.getRole().equals("head master")) {
                    HeadMaster h = new HeadMaster();
                    h.setRole("head master");
                    return h;
                }
            }
        }
        return null;
    }
}
