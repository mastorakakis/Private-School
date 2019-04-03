package entities;

import java.util.Objects;

public class Trainer extends Entity {

    private int tId;
    private String subject;

    public Trainer() {
    }

    public Trainer(int tId, String firstName, String lastName, String subject) {
        super(firstName, lastName);
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
    public String toString() {
        return tId + ". " + getFirstName() + " " + getLastName()
                + " - Subject: " + subject;
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
        if (!Objects.equals(getFirstName(), other.getFirstName())) {
            return false;
        }
        if (!Objects.equals(getLastName(), other.getLastName())) {
            return false;
        }
        return true;
    }

}
