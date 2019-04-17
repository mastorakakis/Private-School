package joinedEntities;

public class AssignmentsPerTrainerPerCourse {

    private int cId;
    private int aId;
    private int tId;

    public AssignmentsPerTrainerPerCourse() {
    }

    public AssignmentsPerTrainerPerCourse(int cId, int aId, int tId) {
        this.cId = cId;
        this.aId = aId;
        this.tId = tId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

}
