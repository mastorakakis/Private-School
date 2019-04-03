package joinedEntitiesDao;

import entitiesDao.GenericDao;
import joinedEntities.AssignmentPerCourse;
import entities.Assignment;
import entities.Course;
import entitiesDao.AssignmentDao;
import entitiesDao.CourseDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myDatabase.MyDatabase;

public class AssignmentPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    AssignmentDao ad = new AssignmentDao();

    public List<AssignmentPerCourse> readAssignmentPerCourseList() {
        String query = "SELECT * FROM private_school.assignment_course;";
        List<AssignmentPerCourse> list = new ArrayList();
        List<Course> courses = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                if (!courses.contains(cd.readByCourseId(rs.getInt("c_id")))) {
                    courses.add(cd.readByCourseId(rs.getInt("c_id")));
                }
            }
            for (Course course : courses) {
                List<Assignment> assignments = new ArrayList();
                rs.beforeFirst();
                while (rs.next()) {
                    if (course.getCid() == rs.getInt("c_id")) {
                        assignments.add(ad.readByAssignmentId(rs.getInt("a_id")));
                    }
                }
                list.add(new AssignmentPerCourse(course, assignments));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerCourseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
