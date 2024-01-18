package view.tm;

public class StudentTm {
    private String studentId;
    private String studentName;
    private String studentCourseId;
    private String studentAddress;
    private String studentBatchId;
    private String gender;
    private String studentContact;
    private String studentEmail;
    private String admissionFee;

    public StudentTm() {
    }

    public StudentTm(String studentId, String studentCourseId, String studentBatchId) {
        this.studentId = studentId;
        this.studentCourseId = studentCourseId;
        this.studentBatchId = studentBatchId;
    }

    public StudentTm(String studentId, String studentName, String studentCourseId, String studentAddress, String studentBatchId, String gender, String studentContact, String studentEmail, String admissionFee) {
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setStudentCourseId(studentCourseId);
        this.setStudentAddress(studentAddress);
        this.setStudentBatchId(studentBatchId);
        this.setGender(gender);
        this.setStudentContact(studentContact);
        this.setStudentEmail(studentEmail);
        this.setAdmissionFee(admissionFee);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(String studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentBatchId() {
        return studentBatchId;
    }

    public void setStudentBatchId(String studentBatchId) {
        this.studentBatchId = studentBatchId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getAdmissionFee() {
        return admissionFee;
    }

    public void setAdmissionFee(String admissionFee) {
        this.admissionFee = admissionFee;
    }

    @Override
    public String toString() {
        return "StudentTm{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentCourseId='" + studentCourseId + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentBatchId='" + studentBatchId + '\'' +
                ", gender='" + gender + '\'' +
                ", studentContact='" + studentContact + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", admissionFee='" + admissionFee + '\'' +
                '}';
    }
}
