package entitiesDao;

import entities.Student;
import entities.User;
import entitiesFunctions.StudentFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

// contains CRUD operations
public class StudentDao extends GenericDao {

    public List<Student> readStudentList() {
        List<Student> list = new ArrayList();
        String query = "SELECT st_id, first_name, last_name, "
                + "            date_of_birth, tuition_fees "
                + "     FROM students;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Student student = new Student(rs.getInt("st_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getDate("date_of_birth"), rs.getInt("tuition_fees"));
                list.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public Student readByStudentId(int id) {
        String query = "SELECT st_id, first_name, last_name, "
                + "            date_of_birth, tuition_fees "
                + "     FROM students"
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Student student = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            student = new Student(rs.getInt("st_id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getDate("date_of_birth"),
                    rs.getInt("tuition_fees"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return student;
    }

    public Student createNewStudent(Scanner sc) {
        UserDao ud = new UserDao();
        User user = ud.createNewUser(sc, "student");
        Student student = StudentFunctions.newStudent(sc);
        String query = "INSERT INTO private_school.students "
                + "         (st_id, first_name, last_name, "
                + "             date_of_birth, tuition_fees, u_id)"
                + "     VALUES (DEFAULT, ?, ?, ?, ?, '" + ud.readMaxUid() + "')";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        StudentFunctions.addToStudentTable(db, pst, student, 0);
        db.closeConnections();
        return student;
    }

    public Student updateByStudentId(int id, Scanner sc) {
        Student student = StudentFunctions.newStudent(sc);
        String query = "UPDATE students "
                + "     SET first_name = ?, last_name = ?, "
                + "     date_of_birth = ?, tuition_fees = ? "
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        StudentFunctions.addToStudentTable(db, pst, student, id);
        db.closeConnections();
        return student;
    }

    public void deleteByStudentId(int id) {
        String query = "DELETE FROM users "
                + "       WHERE u_id = "
                + "         (SELECT u_id FROM students WHERE st_id = ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }

    public Student readStudentbyUid(int id) {
        String query = "SELECT * FROM students "
                + "     WHERE u_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Student student = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            student = new Student(rs.getInt("st_id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getDate("date_of_birth"),
                    rs.getInt("tuition_fees"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return student;
    }
}
