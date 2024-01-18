package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import module.*;
import util.Validation;
import view.tm.SubjectTm;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AddSubjectFormController {
    //public JFXTextField txtSubjectId;
    public JFXTextField txtSubjectName;
    public JFXTextField txtTeacherName;
    public JFXComboBox<String> cmbTeacherId;
    public JFXComboBox<String> cmbExamId;
    public JFXTextField txtTimePeriod;
    public JFXComboBox<String> cmbCourseId;
    public JFXTextField txtCourseName;
    public JFXTextField txtExamName;

    public TableView<SubjectTm> tblSubject;
    public TableColumn colCourseName;
    public TableColumn colSubId;
    public TableColumn colSubjectName;
    public TableColumn colTeacherName;
    public TableColumn colExamName;
    public TableColumn colTimePeriod;
    public Label lblUpdateSubjectId;
    public Label lblSubjectId;
    public JFXButton btnAdd;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    Pattern subjectName = Pattern.compile("^([A-Z][a-z]{2,}|[A-Z][a-z]{2,} [A-Z][a-z]{2,}|[A-Z]{2,})$");
    Pattern timePeriod = Pattern.compile("^([A-Z][a-z]{2,} [A-Z][a-z]{2,})$");

    public void initialize() throws SQLException, ClassNotFoundException {
        setSubjectId();
        lodeCourse();
        lodeTeacher();
        lodeExam();
        setSubjectTable();
        storeValidation();

        cmbCourseId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCourseData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbTeacherId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setTeacherData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbExamId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setExamData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void storeValidation(){
        map.put(txtSubjectName,subjectName);
        map.put(txtTimePeriod,timePeriod);

    }

    public void setSubjectTable() throws SQLException, ClassNotFoundException {
        ObservableList<SubjectTm> subjectList = new SubjectSever().getSubjectList();
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colSubId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        colExamName.setCellValueFactory(new PropertyValueFactory<>("examName"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));

     tblSubject.setItems(subjectList);
    }


    /////setCmbCourse
    public void lodeCourse() throws SQLException, ClassNotFoundException {
        List<String> allCourseIds = new CourseSever().getAllCourseIds();
        cmbCourseId.getItems().addAll(allCourseIds);
    }

    //setTxtCourse
    public void setCourseData(String courseData) throws SQLException, ClassNotFoundException {
        Course course = new CourseSever().passCourse(courseData);
        if (courseData == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            txtCourseName.setText(course.getCourseName());
        }
    }

   /////setCmbTeacher
    public void lodeTeacher() throws SQLException, ClassNotFoundException {
        List<String> allTeacherId = new TeacherSever().getAllTeacherId();
        cmbTeacherId.getItems().addAll(allTeacherId);
    }

    //setTxtTeacher
    public void setTeacherData(String teacherData) throws SQLException, ClassNotFoundException {
        Teacher teacher = new TeacherSever().passTeacher(teacherData);
        if (teacherData==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            txtTeacherName.setText(teacher.getTeacherName());
        }
    }

   /////setCmbExam
    public void lodeExam() throws SQLException, ClassNotFoundException {
        List<String> allExamId = new ExamSever().getAllExamId();
        cmbExamId.getItems().addAll(allExamId);
    }
    //setTxtExam
    public void setExamData(String examData) throws SQLException, ClassNotFoundException {
        Exam exam = new ExamSever().passExam(examData);
        if (examData==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            txtExamName.setText(exam.getExamType());
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SubjectTm subject = tblSubject.getSelectionModel().getSelectedItem();
        String subjectId = subject.getSubjectId();

        if (new SubjectSever().deleteSubject(subjectId)){
            setSubjectId();
            setSubjectTable();
           // new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Saved Subject Data","Successfully SavedSubject Data",0,0);

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
        txtCourseName.clear();
        lblUpdateSubjectId.setText("");
        txtSubjectName.clear();
        txtExamName.clear();
        txtTimePeriod.clear();
        txtTeacherName.clear();

        txtSubjectName.requestFocus();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SubjectTm subjectTm = new SubjectTm(
               lblUpdateSubjectId.getText(),
                txtSubjectName.getText(),
                cmbTeacherId.getValue(),
                cmbExamId.getValue(),
                txtTimePeriod.getText(),
                cmbCourseId.getValue(),
             txtTeacherName.getText(),
             txtExamName.getText(),
             txtCourseName.getText()
        );

        if (new SubjectSever().updateSubject(subjectTm)){
            setSubjectTable();
            clearField();
         //   new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        new AddNotificationForm().
                    sceneNotifications("Updated Subject Data ","Successfully Update Subject Data",0,6);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Subject s1 = new Subject(
                lblSubjectId.getText(),
                txtSubjectName.getText(),
                cmbTeacherId.getValue(),
                cmbExamId.getValue(),
                txtTimePeriod.getText(),
                cmbCourseId.getValue(),
                txtTeacherName.getText(),
                txtExamName.getText(),
                txtCourseName.getText()
        );

        if (new SubjectSever().saveSubject(s1)){
            setSubjectId();
            setSubjectTable();
            clearField();
           // new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();

            new AddNotificationForm().
                    sceneNotifications("Saved Subject Data","Successfully Saved Subject Data",0,0);
        }else{
           // new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }

    private void setSubjectId() {
        try {
            lblSubjectId.setText(new SubjectSever().getSubjectId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {
        SubjectTm subject = null;
        subject = tblSubject.getSelectionModel().getSelectedItem();
        txtCourseName.setText(subject.getCourseName());
        lblUpdateSubjectId.setText(subject.getSubjectId());
        txtSubjectName.setText(subject.getSubjectName());
        txtTeacherName.setText(subject.getTeacherName());
        txtExamName.setText(subject.getExamName());
        txtTimePeriod.setText(subject.getTimePeriod());
        cmbCourseId.setValue(subject.getCourseId());
        cmbTeacherId.setValue(subject.getTeacherId());
        cmbExamId.setValue(subject.getExamId());
    }

    public void btnSubjectRelease(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = Validation.validate(map, btnAdd);

        if (keyEvent.getCode()==KeyCode.ENTER){
            TextField error = (TextField) response;
            error.requestFocus();
        }else if (response instanceof Boolean){
        }
    }
}
