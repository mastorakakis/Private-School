package menus;

import entities.User;
import entitiesDao.GenericDao;
import entitiesDao.RoleDao;
import entitiesDao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import functions.Password;

public class Login extends GenericDao {

    // exit when username = exit
    public static String prompt(Scanner sc) {
        String action;
        List<User> list = new ArrayList();
        list = new UserDao().readUserList();
        System.out.println("");
        System.out.println("Login");
        System.out.print("Username: ");
        String loginUsername = sc.next();
        if (loginUsername.equals("exit")) {
            return "exit";
        }
        System.out.print("Password: ");
        String loginPassword = Password.encrypt(sc.next());
        String check = "not found";
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
        boolean check = user.getRole().equals("trainer");
        switch (user.getRole()) {
            case "student":
                StudentMenu.options(sc, user);
                break;
            case "trainer":
                TrainerMenu.options(sc, user);
                break;
        }
    }
}
