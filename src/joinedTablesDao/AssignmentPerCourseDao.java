package joinedTablesDao;

import entitiesDao.GenericDao;
import joinedTables.AssignmentPerCourse;
import entities.Assignment;
import entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class AssignmentPerCourseDao extends GenericDao {

    public List<AssignmentPerCourse> readAssignmentPerCourseList() {
        List<AssignmentPerCourse> list = new ArrayList();
        String query = "SELECT * "
                + "     FROM assignment "
                + "         INNER JOIN assignment_course USING (a_id) "
                + "             INNER JOIN course USING (c_id) "
                + "                 INNER JOIN stream USING (stream_id) "
                + "                     INNER JOIN c_type USING (type_id) "
                + "     ORDER BY course.title, stream_name, type_name;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Assignment assignment = new Assignment(rs.getInt("a_id"),
                        rs.getString("title"), rs.getString("t_description"),
                        rs.getInt("oral_mark"), rs.getInt("total_mark"),
                        rs.getDate("submission_date"));
                Course course = new Course(rs.getInt("c_id"),
                        rs.getString("title"), rs.getString("stream_name"),
                        rs.getString("type_name"),
                        rs.getDate("start_date"), rs.getDate("end_date"));
                AssignmentPerCourse apc = new AssignmentPerCourse(assignment,
                        course);
                list.add(apc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
