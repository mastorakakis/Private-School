package entities;

import java.util.Objects;

public class Trainer extends Person {

    private int tId;
    private String subject;

    public Trainer() {
    }

    public Trainer(int tId, String firstName, String lastName, String subject) {
        super(firstName, lastName);
        this.tId = tId;
        this.subject = subject;
    }

    public Trainer(int tId, String subject, String firstName, String lastName, String role) {
        super(firstName, lastName, role);
        this.tId = tId;
        this.subject = subject;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        final Trainer other = (Trainer) obj;
        if (!super.equals((Person) obj)) {
            return false;
        }
        if (this.tId != other.tId) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-13s %-17s %s", tId, getFirstName(),
                getLastName(), subject);
    }
}
