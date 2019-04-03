package joinedEntitiesDao;

import entitiesDao.GenericDao;
import entities.Course;
import entities.Trainer;
import entitiesDao.CourseDao;
import entitiesDao.TrainerDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntities.TrainerPerCourse;
import myDatabase.MyDatabase;

public class TrainerPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    TrainerDao td = new TrainerDao();

    public List<TrainerPerCourse> readTrainerPerCourseList() {
        String query = "SELECT * FROM private_school.trainer_course;";
        List<TrainerPerCourse> list = new ArrayList();
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
                List<Trainer> trainers = new ArrayList();
                rs.beforeFirst();
                while (rs.next()) {
                    if (course.getCid() == rs.getInt("c_id")) {
                        trainers.add(td.readByTrainerId(rs.getInt("t_id")));
                    }
                }
                list.add(new TrainerPerCourse(course, trainers));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
