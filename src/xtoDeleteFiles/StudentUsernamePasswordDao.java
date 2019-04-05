package xtoDeleteFiles;

import xtoDeleteFiles.UsernamePasswordDao;
import java.util.List;
import java.util.Scanner;

public class StudentUsernamePasswordDao extends UsernamePasswordDao {

//    public List<UsernamePassword> readStudentUsernamePasswordList() {
//        String query = "SELECT st_id, username, password, role "
//                + "     FROM student_password "
//                + "         INNER JOIN roles ON role_id = id;";
//        return readUsernamePasswordList(query);
//    }
//
//    public UsernamePassword readByStudentUsernamePasswordId(int id) {
//        String query = "SELECT st_id, username, password, role "
//                + "     FROM student_password "
//                + "         INNER JOIN roles ON role_id = id;";
//        return readByUsernamePasswordId(id, query);
//    }
//
//    public UsernamePassword createNewStudentUsernamePassword(Scanner sc) {
//        String query = "INSERT INTO student_password (st_id, "
//                + "                                   username, "
//                + "                                   password, "
//                + "                                   role_id)"
//                + "     VALUES (?, ?, ?, 3);";
//        return createNewUsernamePassword(sc, query);
//    }
//
//    public UsernamePassword updateByStudentUsernamePasswordId(int id,
//            Scanner sc) {
//        String query = "UPDATE student_password "
//                + "     SET username = ?, "
//                + "         password = ?, role_id = 3 "
//                + "     WHERE st_id = ?;";
//        return updateByUsernamePasswordId(id, sc, query);
//    }
}
