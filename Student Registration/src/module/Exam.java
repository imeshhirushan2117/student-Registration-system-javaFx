package module;

public class Exam {
    private String examId;
    private String description;
    private String examType;

    public Exam() {
    }

    public Exam(String examId, String description, String examType) {
        this.setExamId(examId);
        this.setDescription(description);
        this.setExamType(examType);
    }


    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", description='" + description + '\'' +
                ", examType='" + examType + '\'' +
                '}';
    }
}
