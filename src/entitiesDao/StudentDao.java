package entitiesDao;

import entities.Student;
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
        String query = "SELECT st_id, "
                + "            first_name, "
                + "            last_name, "
                + "            date_of_birth, "
                + "            tuition_fees "
                + "     FROM student;";
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
        String query = "SELECT st_id, "
                + "            first_name, "
                + "            last_name, "
                + "            date_of_birth, "
                + "            tuition_fees "
                + "     FROM student"
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        Student student = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            rs.next();
            student = new Student(rs.getInt("st_id"),
                    rs.getString("first_name"), rs.getString("last_name"),
                    rs.getDate("date_of_birth"), rs.getInt("tuition_fees"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return student;
    }

    public Student createNewStudent(Scanner sc) {
        Student student = StudentFunctions.newStudent(sc);
        String query = "INSERT INTO private_school.student (st_id, first_name, "
                + "                                        last_name, "
                + "                                        date_of_birth, "
                + "                                        tuition_fees)"
                + "     VALUES (DEFAULT, ?, ?, ?, ?);";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        StudentFunctions.addToStudentTable(db, pst, student, query, 0);
        db.closeConnections();
        return student;
    }

    public Student updateByStudentId(int id, Scanner sc) {
        Student student = StudentFunctions.newStudent(sc);
        String query = "UPDATE student "
                + "     SET first_name = ?, last_name = ?, "
                + "     date_of_birth = ?, tuition_fees = ? "
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        StudentFunctions.addToStudentTable(db, pst, student, query, id);
        db.closeConnections();
        return student;
    }

    public void deleteByStudentId(int id) {
        String query = "DELETE FROM student WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        db.MyPreparedStatement(id);
        db.closeConnections();
    }
}
