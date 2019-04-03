package joinedTablesDao;

import entitiesDao.GenericDao;
import entities.Course;
import entities.Trainer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedTables.TrainerPerCourse;
import myDatabase.MyDatabase;

public class TrainerPerCourseDao extends GenericDao {

    public List<TrainerPerCourse> readListTrainerPerCourse() {
        List<TrainerPerCourse> list = new ArrayList();
        String query = "SELECT * "
                + "     FROM trainer "
                + "         INNER JOIN trainer_course USING (t_id) "
                + "             INNER JOIN course USING (c_id) "
                + "                 INNER JOIN stream USING (stream_id) "
                + "                     INNER JOIN c_type USING (type_id) "
                + "     ORDER BY course.title, stream_name, type_name;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                Trainer trainer = new Trainer(rs.getInt("t_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("t_subject"));
                Course course = new Course(rs.getInt("c_id"),
                        rs.getString("title"), rs.getString("stream_name"),
                        rs.getString("type_name"),
                        rs.getDate("start_date"), rs.getDate("end_date"));
                TrainerPerCourse tpc = new TrainerPerCourse(trainer, course);
                list.add(tpc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }
}
