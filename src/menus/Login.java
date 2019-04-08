package menus;

import entities.User;
import entitiesDao.GenericDao;
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
        System.out.println("Login");
        System.out.print("Username: ");
        String loginUsername = sc.next();
        if (loginUsername.equals("exit")) {
            return "exit";
        }
        System.out.print("Password: ");
        String loginPassword = Password.encrypt(sc.next());
//        System.out.println(loginPassword);
//        String loginPassword = "96e89a298e0a9f469b9ae458d6afae9f";
        for (User user : list) {
            if (user.getUsername().equals(loginUsername)
                    && user.getPassword().equals(loginPassword)) {
                EntryMenu.options(sc, user);
            } else {
                System.out.println("Wrong username or password");
                return "login again";
            }
        }
        return "login again"; //action;
    }
}
