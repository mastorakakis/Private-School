package joinedEntitiesDao;

import entitiesDao.GenericDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntities.AssignmentsPerTrainerPerCourse;
import myDatabase.MyDatabase;

public class AssignmentsPerTrainerPerCourseDao extends GenericDao {

    public List<AssignmentsPerTrainerPerCourse> readAssignmentsPerTrainerPerCourseList() {
        List<AssignmentsPerTrainerPerCourse> list = new ArrayList();
        String query = "SELECT * "
                + "     FROM private_school.assignments_trainers_course;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet();
        try {
            while (rs.next()) {
                AssignmentsPerTrainerPerCourse atc
                        = new AssignmentsPerTrainerPerCourse(rs.getInt("c_id"),
                                rs.getInt("a_id"), rs.getInt("t_id"));
                list.add(atc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerTrainerPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<AssignmentsPerTrainerPerCourse> readAssignmentsPerTrainerPerCourseByStIdList(int id) {
        List<AssignmentsPerTrainerPerCourse> list = new ArrayList();
        String query = "SELECT * FROM private_school.assignments_trainers_course "
                + "     WHERE t_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        AssignmentsPerTrainerPerCourse atc = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                atc = new AssignmentsPerTrainerPerCourse(rs.getInt("c_id"),
                        rs.getInt("a_id"), rs.getInt("t_id"));
                list.add(atc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerTrainerPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<AssignmentsPerTrainerPerCourse> readAssignmentsPerTrainerPerCourseByCIdList(int id) {
        List<AssignmentsPerTrainerPerCourse> list = new ArrayList();
        String query = "SELECT * FROM private_school.assignments_trainers_course "
                + "     WHERE c_id = ?"
                + "     ORDER BY t_id;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        AssignmentsPerTrainerPerCourse atc = null;
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                atc = new AssignmentsPerTrainerPerCourse(rs.getInt("c_id"),
                        rs.getInt("a_id"), rs.getInt("t_id"));
                list.add(atc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerTrainerPerCourseDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

}
