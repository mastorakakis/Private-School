package menus;

import entities.Assignment;
import entities.Student;
import entities.User;
import entitiesDao.AssignmentDao;
import entitiesDao.StudentDao;
import entitiesDao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to School Data Application");
        System.out.println("(Instructions:  To navigate through the app menus choose a number and press enter)");
        System.out.println("                To exit type 'exit' in the username field");
        String action;
        do {
            action = Login.prompt(sc);
            if (action.equals("exit")) {
                break;
            }
        } while (action.equals("try again"));

//        String query = "insert into students (st_id, first_name, last_name, tuition_fees, u_id) values (default, 'dfsgf', 'sfgfg', 2005, 21)";
//        String URL
//                = "jdbc:mysql://localhost:3306/private_school?serverTimezone=Europe/Athens";
//        String USERNAME = "root";
//        String PASS = "mast3815yq";
//        Connection conn;
//        try {
//            conn = DriverManager.getConnection(URL, USERNAME, PASS);
//            PreparedStatement pst = conn.prepareStatement(query);
//            int result = pst.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        StudentDao sd = new StudentDao();
//        Student s = sd.createNewStudent(sc);
//        System.out.println(s);
    }
}
