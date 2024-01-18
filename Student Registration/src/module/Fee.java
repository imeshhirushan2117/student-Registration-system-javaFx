package module;

public class Fee {
     private String feeId;
     private String selectCourseId;
     private String courseName;
     private double cash;
     private String date;

    public Fee() {
    }

    public Fee(String feeId, String selectCourseId, String courseName, double cash, String date) {
        this.setFeeId(feeId);
        this.setSelectCourseId(selectCourseId);
        this.setCourseName(courseName);
        this.setCash(cash);
        this.setDate(date);
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
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

    @Override
    public String toString() {
        return "Fee{" +
                "feeId='" + feeId + '\'' +
                ", selectCourseId='" + selectCourseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", cash=" + cash +
                ", date='" + date + '\'' +
                '}';
    }
}
