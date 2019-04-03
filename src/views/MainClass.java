package views;

import entities.Person;
import entitiesDao.AssignmentDao;
import entitiesDao.StudentDao;
import java.util.Scanner;
import joinedEntities.StudentPerCourse;
import joinedEntitiesDao.AssignmentPerCourseDao;
import joinedEntitiesDao.StudentPerCourseDao;
import joinedEntitiesDao.TrainerPerCourseDao;
import usernamePassword.UsernamePassword;
import usernamePasswordDao.StudentUsernamePasswordDao;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to School Data Application");
        TrainerPerCourseDao apc = new TrainerPerCourseDao();
        System.out.println(apc.readTrainerPerCourseList());

    }
}
