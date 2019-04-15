package entities;

import entitiesDao.CourseDao;
import entitiesDao.CourseObjectiveDao;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CourseScheduleDate {

    private int cId;
    private int cobId;
    private LocalDate cDate;

    public CourseScheduleDate() {
    }

    public CourseScheduleDate(int c_id, int j_ob_id, Date date) {
        this.cId = c_id;
        this.cobId = j_ob_id;
        this.cDate = date.toLocalDate();
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getCobId() {
        return cobId;
    }

    public void setCobId(int cobId) {
        this.cobId = cobId;
    }

    public LocalDate getCDate() {
        return cDate;
    }

    public void setCDate(LocalDate cDate) {
        this.cDate = cDate;
    }

    @Override
    public String toString() {
        String stream = new CourseDao().readByCourseId(cId).getStream();
        if (stream.equals("c#")) {
            stream = "c";
        }
        return String.format("%-5d %-40s %s", cobId,
                new CourseObjectiveDao().readByCourseObjectiveId(cobId, stream).getTitle(),
                cDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
