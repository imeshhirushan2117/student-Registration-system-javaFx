package view.tm;

public class BatchTm {
    private String batchId;
    private String batchName;
    private String courseId;
    private String courseName;

    public BatchTm() {
    }

    public BatchTm(String batchId, String batchName, String courseId, String courseName) {
        this.setBatchId(batchId);
        this.setBatchName(batchName);
        this.setCourseId(courseId);
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    @Override
    public String toString() {
        return "BatchTm{" +
                "batchId='" + getBatchId() + '\'' +
                ", batchName='" + getBatchName() + '\'' +
                ", courseId='" + getCourseId() + '\'' +
                ", courseName='" + getCourseName() + '\'' +
                '}';
    }
}
