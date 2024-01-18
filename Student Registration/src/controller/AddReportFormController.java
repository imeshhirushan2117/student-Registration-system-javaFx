package controller;

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
import view.tm.StudentTm;

import java.sql.SQLException;

public class AddReportFormController {

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


    public void initialize() throws SQLException, ClassNotFoundException {
        setStudentTable();
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

    public void btnBatchPayment(MouseEvent mouseEvent) {

        try {
            JasperDesign desing = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/batchPayment.jrxml"));
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
}
