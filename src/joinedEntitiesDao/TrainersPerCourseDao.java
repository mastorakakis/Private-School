package joinedEntitiesDao;

import entitiesDao.GenericDao;
import entities.Course;
import entities.Student;
import entities.Trainer;
import entitiesDao.CourseDao;
import entitiesDao.TrainerDao;
import entitiesFunctions.CourseFunctions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinedEntities.TrainersPerCourse;
import joinedEntitiesFunctions.StudentsPerCourseFunctions;
import joinedEntitiesFunctions.TrainersPerCourseFunctions;
import myDatabase.MyDatabase;
import xfunctions.Print;

public class TrainersPerCourseDao extends GenericDao {

    CourseDao cd = new CourseDao();
    TrainerDao td = new TrainerDao();

    public List<TrainersPerCourse> readTrainersPerCourseList() {
        String query = "SELECT * FROM private_school.trainers_course;";
        List<TrainersPerCourse> list = new ArrayList();
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
                    if (course.getcId() == rs.getInt("c_id")) {
                        trainers.add(td.readByTrainerId(rs.getInt("t_id")));
                    }
                }
                list.add(new TrainersPerCourse(course, trainers));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public List<Course> readCoursesPerTrainerByTId(int id) {
        List<Course> courses = new ArrayList();
        CourseDao cd = new CourseDao();
        String query = "SELECT c_id FROM trainers_course "
                + "     WHERE t_id = ?;";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(id);
        try {
            while (rs.next()) {
                courses.add(cd.readByCourseId(rs.getInt("c_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            db.closeConnections();
        }
        return courses;
    }

    public int addToTrainersPerCourse(Scanner sc) {
        int result = 0;
        Print.coursesPerTrainer();
        Trainer trainer = TrainersPerCourseFunctions.getTrainer(sc);
        Course course = CourseFunctions.getCourse(sc);
        List<Trainer> trainers
                = readTrainersPerCourseByCIdList(course.getcId());
        if (trainers.contains(trainer)) {
            System.out.println("Trainer is already in that course");
            return result;
        }
        String query = "INSERT INTO trainers_course "
                + "VALUES (" + trainer.gettId() + ", " + course.getcId() + ");";
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        try {
            result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        addToAssignmentPerTrainerPerCourse(trainer, course);
        return result;
    }

    public List<Trainer> readTrainersPerCourseByCIdList(int cId) {
        String query = "SELECT * FROM private_school.trainers_course"
                + "         INNER JOIN trainers USING (t_id) "
                + "     WHERE c_id = ?;";
        List<Trainer> list = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(cId);
        try {
            while (rs.next()) {
                Trainer trainer = new Trainer(rs.getInt("t_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("t_subject"));
                list.add(trainer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        return list;
    }

    public void addToAssignmentPerTrainerPerCourse(Trainer trainer, Course course) {
        List<Integer> aIds
                = new AssignmentsPerCourseDao().readAssignmentsIdsPerCourseByCIdList(course.getcId());
        for (Integer aId : aIds) {
            String query = "INSERT INTO assignments_trainers_course "
                    + "                 (c_id, a_id, t_id) "
                    + "     VALUES      (" + course.getcId() + ", " + aId + ", "
                    + trainer.gettId() + ");";
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            PreparedStatement pst = db.MyPreparedStatement();
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnections();
            }
        }
    }

    public int removeFromTrainersPerCourse(Scanner sc) {
        int result = 0;
        Print.coursesPerTrainer();
        Trainer trainer = TrainersPerCourseFunctions.getTrainer(sc);
        Course course = CourseFunctions.getCourse(sc);
        List<Trainer> trainers
                = readTrainersPerCourseByCIdList(course.getcId());
        if (!trainers.contains(trainer)) {
            System.out.println("Trainer is not in that course");
            return result;
        }
        String query = "DELETE FROM trainers_course "
                + "     WHERE t_id = " + trainer.gettId() + " AND c_id = "
                + course.getcId();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        PreparedStatement pst = db.MyPreparedStatement();
        try {
            result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();
        }
        removeFromAssignmentsPerTrainerPerCourse(trainer, course);
        return result;
    }

    public void removeFromAssignmentsPerTrainerPerCourse(Trainer trainer, Course course) {
        List<Integer> aIds
                = new AssignmentsPerCourseDao().readAssignmentsIdsPerCourseByCIdList(course.getcId());
        for (Integer aId : aIds) {
            String query = "Delete FROM assignments_trainers_course "
                    + "     WHERE t_id = " + trainer.gettId() + " AND c_id = "
                    + course.getcId() + " AND a_id = " + aId;
            MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
            PreparedStatement pst = db.MyPreparedStatement();
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnections();
            }
        }
    }

    public List<Integer> readTrainersIdsPerCourseByCIdList(int cId) {
        String query = "SELECT * FROM private_school.trainers_course"
                + "         INNER JOIN trainers USING (t_id) "
                + "     WHERE c_id = ?;";
        List<Integer> tIds = new ArrayList();
        MyDatabase db = new MyDatabase(URL, USERNAME, PASS, query);
        ResultSet rs = db.MyResultSet(cId);
        try {
            while (rs.next()) {
                int tId = rs.getInt("t_id");
                tIds.add(tId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainersPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnections();

        }
        return tIds;
    }
}
