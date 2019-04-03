package menus;

import entities.Person;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to School Data Application");
        System.out.println("(Instructions:  To navigate through the app menus choose a number and press enter)");
        System.out.println("                To exit type 'exit' in the username field");
        Person user;
        do {
            System.out.println("User Login:");
            System.out.print("Enter Username: ");
//            String loginUsername = sc.next();
            String loginUsername = "head";
            if (loginUsername.equals("exit")) {
                break;
            }
            user = Login.prompt(sc, loginUsername);
            if (user == null) {
                System.out.println("Wrong username or password");
                continue;
            }
            user = EntryMenu.options(sc, user);
        } while (user == null);
    }
}
