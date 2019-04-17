package joinedEntities;

import entitiesDao.AssignmentDao;

public class AssignmentsPerStudentPerCourse {

    private int cId;
    private int aId;
    private int stId;
    private int oralMark;
    private int totalMark;
    private boolean submitted;

    public AssignmentsPerStudentPerCourse() {
    }

    public AssignmentsPerStudentPerCourse(int cId, int aId, int stId, int oralMark, int totalMark, boolean submitted) {
        this.cId = cId;
        this.aId = aId;
        this.stId = stId;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.submitted = submitted;
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

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
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

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    @Override
    public String toString() {
        return "A-ID:" + aId
                + "\nTITLE: " + new AssignmentDao().readByAssignmentId(aId).getTitle()
                + "\nDESCRIPTION: " + new AssignmentDao().readByAssignmentId(aId).getDescription();
    }
}
