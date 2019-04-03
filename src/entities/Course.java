package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Course extends Entity {

    private int cid;
    private String title;
    private String stream = " ";
    private String type = " ";
    private LocalDate startDate;
    private LocalDate endDate;

    public Course(int cid, String title, String stream, String type, Date startDate,
            Date endDate) {
        this.cid = cid;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate.toLocalDate();
        this.endDate = endDate.toLocalDate();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Course() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.stream, other.stream)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cid + ". " + title + " - " + stream + "(" + type + ")\n"
                + "Duration: "
                + startDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + " - "
                + endDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }
}
