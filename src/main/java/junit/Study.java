package junit;

public class Study {

    private StudyStatus studyStatus = StudyStatus.DRAFT;

    private int limit;

    public StudyStatus getStatus() {
        return this.studyStatus;
    }

    public int getLimit() {
        return limit;
    }
}
