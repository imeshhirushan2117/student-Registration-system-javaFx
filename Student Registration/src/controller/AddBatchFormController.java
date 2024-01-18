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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import module.Batch;
import module.Course;
import util.Validation;
import view.tm.BatchTm;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddBatchFormController {
    //public JFXTextField txtBatchId;
    public JFXTextField txtBatchName;
    public JFXComboBox<String> cmbCourseId;
    public JFXTextField txtCourseName;
    public AnchorPane anchorBatch;

    public TableView<BatchTm> tblBatch;

    public TableColumn colBatchId;
    public TableColumn colBatchName;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public Label lblUpdateBatchId;
    public Label lblBatchId;
    public JFXButton btnAdd;
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern batchName = Pattern.compile("^[A-Z]{3}(_)[0-9]{2}$");

    public void btnAddBatchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Batch b1 = new Batch(

                lblBatchId.getText(),
                txtBatchName.getText(),
                cmbCourseId.getValue(),
                txtCourseName.getText()

        );

        if (new BatchSever().saveBatch(b1)){
          //  new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            getBatchId();
            new AddNotificationForm().
                    sceneNotifications("Saved Batch Data","Successfully Saved Batch Data",0,0);
            setBatchTable();
            clearField();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
        //Stage stage = (Stage) anchorBatch.getScene().getWindow();stage.close();
    }

    private void storeValaidation(){
        map.put(txtBatchName,batchName);
    }
    //set cmb
    public void loadCourse() throws SQLException, ClassNotFoundException {
        List<String>courseId = new CourseSever().getAllCourseIds();
        cmbCourseId.getItems().addAll(courseId);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
         setBatchTable();
         getBatchId();
         storeValaidation();

        try {
            loadCourse();

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
    }

    //setTxt
    public void setCourseData(String courseData) throws SQLException, ClassNotFoundException {
        Course course = new CourseSever().passCourse(courseData);
        if (courseData == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            txtCourseName.setText(course.getCourseName());
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        BatchTm batch = new BatchTm(
                lblUpdateBatchId.getText(),
                txtBatchName.getText(),
                cmbCourseId.getValue(),
                txtCourseName.getText()
        );

       if (new BatchSever().updateBatch(batch)){

           //new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
           new AddNotificationForm().
                   sceneNotifications("Updated Batch Data ","Successfully Update Batch Data",0,6);
           setBatchTable();
           clearField();
       }else{
           new Alert(Alert.AlertType.WARNING, "Try Again").show();
       }
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        BatchTm batch = tblBatch.getSelectionModel().getSelectedItem();
        String batchId = batch.getBatchId();

        if (new BatchSever().deleteBatch(batchId)){
            getBatchId();
            setBatchTable();
            //new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Delete Batch Data ","Successfully Deleted Batch Data",0,2);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();
    }

    public void setBatchTable() throws SQLException, ClassNotFoundException {
        ObservableList<BatchTm> batchList = new BatchSever().getBatchList();

        colBatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        colBatchName.setCellValueFactory(new PropertyValueFactory<>("batchName"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        tblBatch.setItems(batchList);

    }

    public void clearField(){
        lblUpdateBatchId.setText("");
        txtBatchName.clear();
        txtCourseName.clear();

        txtBatchName.requestFocus();
    }

    public void btnClearDataOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void getBatchId(){
        try {
            lblBatchId.setText(new BatchSever().getBatchId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {

        BatchTm batch = null;
        batch = tblBatch.getSelectionModel().getSelectedItem();
        lblUpdateBatchId.setText(batch.getBatchId());
        txtBatchName.setText(batch.getBatchName());
        txtCourseName.setText(batch.getCourseName());
        cmbCourseId.setValue(batch.getCourseId());
    }

    public void btnBatchRelesed(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField error = (TextField) response;
            error.requestFocus();
        } else if (response instanceof Boolean) {
        }
    }
}
