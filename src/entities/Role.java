package entities;

public class Role {

    private int rId;
    private String role;

    public Role() {
    }

    public Role(int rId, String role) {
        this.rId = rId;
        this.role = role;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
