package module;

public class Batch {
    private String batchId;
    private String batchName;
    private String selectCourseId;
    private String courseName;

    public Batch() {
    }

    public Batch(String batchId, String batchName, String selectCourseId, String courseName) {
        this.setBatchId(batchId);
        this.setBatchName(batchName);
        this.setSelectCourseId(selectCourseId);
        this.setCourseName(courseName);
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getSelectCourseId() {
        return selectCourseId;
    }

    public void setSelectCourseId(String selectCourseId) {
        this.selectCourseId = selectCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId='" + batchId + '\'' +
                ", batchName='" + batchName + '\'' +
                ", selectCourseId='" + selectCourseId + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
