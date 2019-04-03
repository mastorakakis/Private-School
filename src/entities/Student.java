package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Student extends Entity {

    private int stId;
    private LocalDate dateOfBirth;
    private int tuitionFees;

    public Student() {
    }

    public Student(int sid, String firstName, String lastName,
            Date dateOfBirth, int tuitionFees) {
        super(firstName, lastName);
        this.stId = sid;
        this.dateOfBirth = dateOfBirth.toLocalDate();
        this.tuitionFees = tuitionFees;
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return stId + ". " + getFirstName() + " " + getLastName() + " Birth Date: "
                + dateOfBirth.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + "\n   Tuition Fees: " + tuitionFees;
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.getFirstName(), other.getFirstName())) {
            return false;
        }
        if (!Objects.equals(this.getLastName(), other.getLastName())) {
            return false;
        }
        return true;
    }

}
