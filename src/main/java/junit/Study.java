package junit;

public class Study {

    private StudyStatus studyStatus = StudyStatus.DRAFT;

    private int limit;

    public Study() {
    }

    public Study(final int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.studyStatus;
    }

    public int getLimit() {
        return limit;
    }
}
