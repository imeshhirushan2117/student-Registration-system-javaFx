package view.tm;

public class SubjectTm {
    private String subjectId;
    private String subjectName;
    private String teacherId;
    private String ExamId;
    private String timePeriod;
    private String courseId;
    private String teacherName;
    private String examName;
    private String courseName;

    public SubjectTm() {
    }

    public SubjectTm(String subjectName, String timePeriod, String teacherName) {
        this.subjectName = subjectName;
        this.timePeriod = timePeriod;
        this.teacherName = teacherName;
    }

    public SubjectTm(String subjectId, String subjectName, String teacherId, String examId, String timePeriod, String courseId, String teacherName, String examName, String courseName) {
        this.setSubjectId(subjectId);
        this.setSubjectName(subjectName);
        this.setTeacherId(teacherId);
        setExamId(examId);
        this.setTimePeriod(timePeriod);
        this.setCourseId(courseId);
        this.setTeacherName(teacherName);
        this.setExamName(examName);
        this.setCourseName(courseName);
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getExamId() {
        return ExamId;
    }

    public void setExamId(String examId) {
        ExamId = examId;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "SubjectTm{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", ExamId='" + ExamId + '\'' +
                ", timePeriod='" + timePeriod + '\'' +
                ", courseId='" + courseId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", examName='" + examName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
