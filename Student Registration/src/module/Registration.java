package module;

public class Registration {

    private String batchId;
    private String studentId;
    private String date;

    public Registration() {
    }

    public Registration(String batchId, String studentId, String date) {
        this.setBatchId(batchId);
        this.setStudentId(studentId);
        this.setDate(date);
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "batchId='" + batchId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
