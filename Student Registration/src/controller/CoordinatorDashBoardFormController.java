package controller;

import com.jfoenix.controls.JFXButton;
import db.DbConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.FeeTm;
import view.tm.StudentTm;
import view.tm.SubjectTm;

import javax.swing.*;
import java.sql.SQLException;

public class CoordinatorDashBoardFormController {
    public TableView<StudentTm> tblCourseDetails;
    public TableColumn colCourseName;
    public TableColumn colBatchName;
    public TableColumn colStudentCount;


    public TableView<SubjectTm> tblStudentDetails;
    public TableColumn colSubjectName;
    public TableColumn colTeacherName;
    public TableColumn colTimePeriod;

    public TableView<FeeTm> tblFeeDetails;
    public TableColumn colFeeCourseName;
    public TableColumn colFee;
    public JFXButton btnRegister;


    public void initialize() throws SQLException, ClassNotFoundException {

        setStudentTableData();
        setSubjectTableData();
        setFeeTable();
    }

    private void setStudentTableData() throws SQLException, ClassNotFoundException {

        ObservableList<StudentTm> studentDataList = new CoordinatorDashBoardSever().getStudentData();

        colCourseName.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colBatchName.setCellValueFactory(new PropertyValueFactory<>("studentCourseId"));
        colStudentCount.setCellValueFactory(new PropertyValueFactory<>("studentBatchId"));

        tblCourseDetails.setItems(studentDataList);

    }

    private void setSubjectTableData() throws SQLException, ClassNotFoundException {
        ObservableList<SubjectTm> subjectDataList = new CoordinatorDashBoardSever().getSubjectData();
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));

        tblStudentDetails.setItems(subjectDataList);
    }

    private void setFeeTable() throws SQLException, ClassNotFoundException {
        ObservableList<FeeTm> feeDataList = new CoordinatorDashBoardSever().getFeeData();
        colFeeCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("cash"));

        tblFeeDetails.setItems(feeDataList);
    }


    public void btnPrintBatchDetails(MouseEvent mouseEvent) {

        try {
            JasperDesign desing = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/Batch Details.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(desing);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnPrintSubjectDetails(MouseEvent mouseEvent) {
        try {
            JasperDesign desing = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/Subject Details.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(desing);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnPrintFeeDetails(MouseEvent mouseEvent) {
        try {
            JasperDesign desing = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/Fee Details.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(desing);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void btnPrintRegister(MouseEvent mouseEvent) {
        try {
            JasperDesign desing = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/RegisterStudent.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(desing);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnPrintStudent(MouseEvent mouseEvent) {
    }

}
