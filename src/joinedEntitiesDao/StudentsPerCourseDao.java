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
import joinedEntities.StudentsPerCourse;
import myDatabase.MyDatabase;

public class StudentsPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    StudentDao sd = new StudentDao();

    public List<StudentsPerCourse> readStudentsPerCourseList() {
        String query = "SELECT * FROM private_school.students_course;";
        List<StudentsPerCourse> spcList = new ArrayList();
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
                    if (course.getcId() == rs.getInt("c_id")) {
                        students.add(sd.readByStudentId(rs.getInt("st_id")));
                    }
                }
                spcList.add(new StudentsPerCourse(course, students));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return spcList;
    }

    public List<Course> readCoursesPerStudentByStId(int id) {
        List<Course> courses = new ArrayList();
        CourseDao cd = new CourseDao();
        String query = "SELECT c_id FROM students_course "
                + "     WHERE st_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                courses.add(cd.readByCourseId(rs.getInt("c_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return courses;
    }
}
