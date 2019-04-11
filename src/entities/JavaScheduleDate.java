package entities;

import entitiesDao.JavaObjectiveDao;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JavaScheduleDate {

    private int cId;
    private int jobId;
    private LocalDate cDate;

    public JavaScheduleDate() {
    }

    public JavaScheduleDate(int c_id, int j_ob_id, Date date) {
        this.cId = c_id;
        this.jobId = j_ob_id;
        this.cDate = date.toLocalDate();
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public LocalDate getCDate() {
        return cDate;
    }

    public void setCDate(LocalDate cDate) {
        this.cDate = cDate;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-40s %s", jobId,
                new JavaObjectiveDao().readByJavaObjectiveId(jobId).getTitle(),
                cDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
