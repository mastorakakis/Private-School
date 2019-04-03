package joinedEntitiesDao;

import entitiesDao.GenericDao;
import entities.Course;
import entities.Student;
import entitiesDao.CourseDao;
import entitiesDao.StudentDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntities.StudentPerCourse;
import myDatabase.MyDatabase;

public class StudentPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    StudentDao sd = new StudentDao();

    public List<StudentPerCourse> readStudentPerCourseList() {
        String query = "SELECT * FROM private_school.student_course;";
        List<StudentPerCourse> list = new ArrayList();
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
                List<Student> students = new ArrayList();
                rs.beforeFirst();
                while (rs.next()) {
                    if (course.getCid() == rs.getInt("c_id")) {
                        students.add(sd.readByStudentId(rs.getInt("st_id")));
                    }
                }
                list.add(new StudentPerCourse(course, students));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
