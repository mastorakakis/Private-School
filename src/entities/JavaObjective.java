package entities;

import java.util.List;

public class JavaObjective {

    private int id;
    private String title;

    public JavaObjective() {
    }

    public JavaObjective(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-15s", id, title);
    }

}
