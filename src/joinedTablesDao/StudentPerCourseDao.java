package joinedTablesDao;

import entitiesDao.GenericDao;
import entities.Course;
import entities.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedTables.StudentPerCourse;
import myDatabase.MyDatabase;

public class StudentPerCourseDao extends GenericDao {

    public List<StudentPerCourse> readStudentPerCourseList() {
        List<StudentPerCourse> list = new ArrayList();
        String query = "SELECT * "
                + "     FROM student "
                + "         INNER JOIN student_course USING (st_id) "
                + "             INNER JOIN course USING (c_id) "
                + "                 INNER JOIN stream USING (stream_id) "
                + "                     INNER JOIN c_type USING (type_id) "
                + "     ORDER BY course.title, stream_name, type_name;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Student student = new Student(rs.getInt("st_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getDate("date_of_birth"), rs.getInt("tuition_fees"));
                Course course = new Course(rs.getInt("c_id"),
                        rs.getString("title"), rs.getString("stream_name"),
                        rs.getString("type_name"),
                        rs.getDate("start_date"), rs.getDate("end_date"));
                StudentPerCourse spc = new StudentPerCourse(student, course);
                list.add(spc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
