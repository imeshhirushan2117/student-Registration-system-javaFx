package view.tm;

public class TeacherTm {
    private String teacherId;
    private String teacherName;
    private String teacherAddress;
    private String teacherContact;
    private String teacherEmail;
    private String gender;

    public TeacherTm() {
    }


    public TeacherTm(String teacherId, String teacherName, String teacherAddress, String teacherContact, String teacherEmail, String gender) {
        this.setTeacherId(teacherId);
        this.setTeacherName(teacherName);
        this.setTeacherAddress(teacherAddress);
        this.setTeacherContact(teacherContact);
        this.setTeacherEmail(teacherEmail);
        this.setGender(gender);
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherContact() {
        return teacherContact;
    }

    public void setTeacherContact(String teacherContact) {
        this.teacherContact = teacherContact;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "TeacherTm{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherAddress='" + teacherAddress + '\'' +
                ", teacherContact='" + teacherContact + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
