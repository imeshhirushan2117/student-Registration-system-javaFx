package view.tm;

public class FeeTm {
    private String feeId;
    private String courseId;
    private double cash;
    private String date;
    private String courseName;

    public FeeTm() {
    }

    public FeeTm(double cash, String courseName) {
        this.cash = cash;
        this.courseName = courseName;
    }

    public FeeTm(String feeId, String courseId, double cash, String date, String courseName) {
        this.setFeeId(feeId);
        this.setCourseId(courseId);
        this.setCash(cash);
        this.setDate(date);
        this.setCourseName(courseName);
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "FeeTm{" +
                "feeId='" + feeId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", cash=" + cash +
                ", date='" + date + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
