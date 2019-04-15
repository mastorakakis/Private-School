package menus;

import entities.Student;
import entitiesDao.StudentDao;
import java.util.List;
import java.util.Scanner;
import xfunctions.Print;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to School Data Application");
        System.out.println("(Instructions:  To navigate through the app menus choose a number and press enter)");
        System.out.println("                To exit type 'exit' in the username field");

//testing area
        String action;
        do {
            action = Login.prompt(sc);
            if (action.equals("exit")) {
                break;
            }
        } while (action.equals("login again"));
    }
}
