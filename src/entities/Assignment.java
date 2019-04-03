package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Assignment extends Person {

    private int aid;
    private String title;
    private String description;
    private LocalDate submissionDate;
    private int oralMark = 10;
    private int totalMark;

    public Assignment() {
    }

    public Assignment(int aid, String title, String description, int oralMark,
            int totalMark, Date submissionDate) {
        this.aid = aid;
        this.title = title;
        this.description = description;
        this.submissionDate = submissionDate.toLocalDate();
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Assignment other = (Assignment) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return aid + ". " + title + "\n   Description: " + description
                + "\n   Oral Mark: " + oralMark + " / Total Mark: "
                + totalMark + "\n   Submission Date: "
                + submissionDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }
}
