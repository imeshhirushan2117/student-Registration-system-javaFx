package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import module.Exam;
import util.Validation;
import view.tm.ExamTm;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddExamFormController {
    //public JFXTextField txtExamId;
    public JFXTextField txtExamType;
    public JFXTextField txtExamDescription;

    public TableView<ExamTm> tblExam;
    public TableColumn colExamId;
    public TableColumn colExamType;
    public TableColumn colDescription;
    public Label lblUpdateExamId;
    public Label lblExamId;
    public JFXButton btnAdd;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern examType = Pattern.compile("^[A-Z]{1}[a-z]{2,} (Exam)|[A-Z]{2,} (Exam)$");
    Pattern description = Pattern.compile("^[A-Z]{1}[a-z]{2,} (Topic)|[A-Z]{2,} (Topic)$");

    public void initialize() throws SQLException, ClassNotFoundException {
        setExamTable();
        setExamId();
        storeValidation();
    }

    private void storeValidation(){
    map.put(txtExamType,examType);
    map.put(txtExamDescription,description);
    }

    public void clickMouseOnAction(){
        ExamTm exam= null;
        exam = tblExam.getSelectionModel().getSelectedItem();
        lblUpdateExamId.setText(exam.getExamId());
        txtExamDescription.setText(exam.getDescription());
        txtExamType.setText(exam.getExamType());
    }

    public void clearField(){
        lblUpdateExamId.setText("");
        txtExamDescription.clear();
        txtExamType.clear();

        txtExamType.requestFocus();
    }

    public void btnClearDataOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void btnAddFeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Exam e1 = new Exam(
                lblExamId.getText(),
                txtExamDescription.getText(),
                txtExamType.getText()
        );

        if (new ExamSever().SaveExam(e1)){
            setExamId();
            setExamTable();
            clearField();
           // new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            new AddNotificationForm().
                    sceneNotifications("Saved Student Exam","Successfully Saved Exam Data",0,0);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ExamTm exam = new ExamTm(
                lblUpdateExamId.getText(),
                txtExamType.getText(),
                txtExamDescription.getText()

        );
        if (new ExamSever().updateExam(exam)){

            //new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();


            new AddNotificationForm().
                    sceneNotifications("Updated Student Exam ","Successfully Update Exam Data",0,6);
            setExamTable();
            clearField();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ExamTm exam = tblExam.getSelectionModel().getSelectedItem();
        String examId = exam.getExamId();

        if (new ExamSever().deleteExam(examId)){
           // new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            setExamId();
            setExamTable();
            clearField();
            new AddNotificationForm().
                    sceneNotifications("Delete Student Exam ","Successfully Deleted Exam Data",0,2);

        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Try Again..").show();
        }
    }

    public void setExamTable() throws SQLException, ClassNotFoundException {
        ObservableList<ExamTm> examList = new ExamSever().getExamList();

        colExamId.setCellValueFactory(new PropertyValueFactory<>("examId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colExamType.setCellValueFactory(new PropertyValueFactory<>("examType"));

        tblExam.setItems(examList);
    }

    private void setExamId() {
        try {
            lblExamId.setText(new ExamSever().getExamId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnExamRelease(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField error = (TextField) response;
            error.requestFocus();
        } else if (response instanceof Boolean) {
        }
    }
}
