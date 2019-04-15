package menus;

import entities.User;
import entitiesDao.GenericDao;
import entitiesDao.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import xfunctions.Password;

public class Login extends GenericDao {

    // exit when username = exit
    public static String prompt(Scanner sc) {
        List<User> list = new ArrayList();
        list = new UserDao().readUserList();
        System.out.println("");
        System.out.println("Login");
        System.out.print("Username: ");
//        String loginUsername = sc.next();
//        if (loginUsername.equals("exit")) {
//            return "exit";
//        }
//        System.out.print("Password: ");
//        String loginPassword = Password.encrypt(sc.next());
        String check = "not found";

        String loginUsername = "head";
        String loginPassword = "96e89a298e0a9f469b9ae458d6afae9f";

        for (User user : list) {
            if (user.getUsername().equals(loginUsername)
                    && user.getPassword().equals(loginPassword)) {
                enter(sc, user);
                check = "found";
            }
        }
        if (!check.equals("found")) {
            System.out.println("Wrong username or password");
        }
        return "login again"; //action;
    }

    public static void enter(Scanner sc, User user) {
        switch (user.getRole()) {
            case "student":
                StudentMenu.options(sc, user);
                break;
            case "trainer":
                TrainerMenu.options(sc, user);
                break;
            case "head master":
                HeadMasterStudentMenu.options(sc, user);
                break;
        }
    }
}
