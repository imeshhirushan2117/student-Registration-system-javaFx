package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import module.Batch;
import module.Course;
import module.Registration;
import module.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Validation;
import view.tm.StudentTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class AddStudentFormController {


    //public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentContact;
    public JFXTextField txtStudentEmail;

    public JFXTextField txtCourseName;
    public JFXTextField txtBatchName;

    public JFXComboBox<String>  cmbSelectGender;
    public JFXComboBox<String>  cmbCourseId;
    public JFXComboBox<String>  cmbBatchId;
    public JFXComboBox<String> cmbAdmissionFee;

    public TableView<StudentTm> tblStudent;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colCourseName;
    public TableColumn colBatchName;
    public TableColumn colGender;
    public TableColumn colDate;
    public TableColumn colAdmissionFee;

    public JFXButton btnAdd;
    public Label lblDate;
    public Label lblStudentId;
    public Label lblStudentId2;
    public JFXButton btnAddStudent;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    Pattern studentName= Pattern.compile("^[A-Z][a-z]{3,} [A-Z][a-z]{3,}$");
    Pattern address = Pattern.compile("^[A-Z][a-z]{3,}$");
    Pattern contact = Pattern.compile("^(077|075|072|076|071|038)[-][0-9]{7}$");
    Pattern email = Pattern.compile("^[a-z0-9]{2,}[@][a-z]{5}(.)[a-z]{3}$");


    private void storeValidation(){
        map.put(txtStudentName,studentName);
        map.put(txtStudentAddress,address);
        map.put(txtStudentContact,contact);
        map.put(txtStudentEmail,email);
    }

    public void clearField(){
        lblStudentId2.setText("");
        //lblStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentContact.clear();
        txtStudentEmail.clear();
        txtCourseName.clear();
        txtBatchName.clear();

        txtStudentName.requestFocus();

    }

    public void initialize() throws SQLException, ClassNotFoundException {

        storeValidation();
        setStudentId();
        cmbSelectGender.getItems().setAll("Male","Female");
        setDate();
        setStudentTable();
        cmbAdmissionFee.getItems().setAll("5000.00/=");
        try {
            lodeCourseId();
            lodeBatchId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCourseId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCourseData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbBatchId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setBatchData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public void setDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    public void setCourseData(String courseData) throws SQLException, ClassNotFoundException {
        Course course = new CourseSever().passCourse(courseData);
        if (courseData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtCourseName.setText(course.getCourseName());
        }
    }

    public void setBatchData(String batchData) throws SQLException, ClassNotFoundException {
        Batch batch = new BatchSever().passBatch(batchData);
        if (batchData == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtBatchName.setText(batch.getBatchName());
        }
    }

    public void setStudentTable() throws SQLException, ClassNotFoundException {
        ObservableList<StudentTm> studentList = new StudentSever().getStudentList();

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("studentCourseId"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colBatchName.setCellValueFactory(new PropertyValueFactory<>("studentBatchId"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colAdmissionFee.setCellValueFactory(new PropertyValueFactory<>("admissionFee"));
        tblStudent.setItems(studentList);
    }

    public void lodeCourseId() throws SQLException, ClassNotFoundException {
        List<String> courseName = new CourseSever().getAllCourseIds();
        cmbCourseId.getItems().addAll(courseName);
    }

    public void lodeBatchId() throws SQLException, ClassNotFoundException {

        List<String> batchName = new BatchSever().getAllBatchId();
        cmbBatchId.getItems().addAll(batchName);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        StudentTm student = tblStudent.getSelectionModel().getSelectedItem();
        String studentId = student.getStudentId();

        if (new StudentSever().deleteStudent(studentId)){
            
            setStudentId();
            setStudentTable();
            //new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Delete Student Data ","Successfully Deleted Student Data",0,2);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        StudentTm student = new StudentTm(
                lblStudentId2.getText(),
                txtStudentName.getText(),
                cmbCourseId.getValue(),
                txtStudentAddress.getText(),
                cmbBatchId.getValue(),
                cmbSelectGender.getValue(),
                txtStudentContact.getText(),
                txtStudentEmail.getText(),
                cmbAdmissionFee.getValue()
        );

        if (new StudentSever().updateStudent(student)){
           // new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            new AddNotificationForm().
                    sceneNotifications("Updated Student Data ","Successfully Update Student Data",0,6);
            setStudentTable();
            clearField();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        List<Registration> registration = new ArrayList<>();
        registration.add(new Registration(

                cmbBatchId.getValue(),
                lblStudentId.getText(),
                lblDate.getText()
        ));

        Student student = new Student(

                lblStudentId.getText(),
                txtStudentName.getText(),
                cmbCourseId.getValue(),
                txtStudentAddress.getText(),
                cmbBatchId.getValue(),
                cmbSelectGender.getValue(),
                txtStudentContact.getText(),
                txtStudentEmail.getText(),
                cmbAdmissionFee.getValue(),
                registration

        );

        System.out.println(student);

        if (new StudentSever().saveStudent(student)){

            setStudentId();
            clearField();
           // new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            new AddNotificationForm().
                    sceneNotifications("Student Saved Data","Successfully Saved Student  Data",0,0);

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }
        setStudentTable();
    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {

        StudentTm student = null;

        student=tblStudent.getSelectionModel().getSelectedItem();

        lblStudentId2.setText(student.getStudentId());
        txtStudentName.setText(student.getStudentName());
        txtStudentAddress.setText(student.getStudentAddress());
        txtStudentContact.setText(student.getStudentContact());
        cmbCourseId.setValue(student.getStudentCourseId());
        cmbBatchId.setValue(student.getStudentBatchId());
        cmbSelectGender.setValue(student.getGender());
        txtStudentEmail.setText(student.getStudentEmail());
        cmbAdmissionFee.setValue(student.getAdmissionFee());
    }

    private void setStudentId() {
        try {
            lblStudentId.setText(new StudentSever().getStudentId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnPrintRegister(MouseEvent mouseEvent) {
        /*JasperDesign desing = null;
        try {
            desing = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/report/StudentBill.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(desing);
            String studentName = txtStudentName.getText();
            String courseName = txtCourseName.getText();
            String batchName = txtBatchName.getText();
            HashMap map1 = new HashMap();

            map1.put("Student Name",studentName);
            map1.put("Course Name",courseName);
            map1.put("Batch Name",batchName);

            ObservableList<StudentTm> studentTms = tblStudent.getItems();

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map1, new JRBeanArrayDataSource(studentTms.toArray()));
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }*/

    }

    public void btnStudentRelesed(KeyEvent keyEvent) {

        btnAdd.setDisable(true);
        Object respones = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER){
            TextField error = (TextField) respones;
            error.requestFocus();
        }else if (respones instanceof Boolean) {
        }
    }
}
