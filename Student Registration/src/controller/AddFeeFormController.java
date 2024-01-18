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
import module.Course;
import module.Fee;
import util.Validation;
import view.tm.FeeTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddFeeFormController {
    //public JFXTextField txtFeeId;
    public JFXComboBox<String> cmbCourseId;
    public JFXTextField txtCourseName;
    public JFXTextField txtCash;
    public JFXTextField txtDate;
    public AnchorPane anchorFee;
    public Label lblDate;
    public TableView<FeeTm> tblFee;

    public TableColumn colFeeId;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colCash;
    public TableColumn colDate;
    public Label lblUpdateFeeId;
    public Label lblFeeId;
    public JFXButton btnAdd;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    //Pattern courseName = Pattern.compile("^[A-Z][a-z]{3,} [A-Z][a-z]{3,}|[A-Z]{2,} [A-Z]{1}[a-z]{3,}$\"");
    Pattern cash = Pattern.compile("^[0-9]{5}$");

    public void initialize() throws SQLException, ClassNotFoundException {
        setDate();
        setFeeTable();
        setFeeId();
        storevalidadtion();

        try {
            lodeCourse();

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

    private void storevalidadtion(){
       // map.put(txtCourseName,courseName);
        map.put(txtCash,cash);
    }




    public void btnAddFeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Fee f1 = new Fee(
                lblFeeId.getText(),
                cmbCourseId.getValue(),
                txtCourseName.getText(),
                Double.parseDouble(txtCash.getText()),
                lblDate.getText()
        );

      // System.out.println(f1);

        if (new FeeSever().addFee(f1)) {
            //new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            new AddNotificationForm().
                    sceneNotifications("Saved Fee Data","Successfully Saved Fee Data",0,0);
            setFeeId();
            setFeeTable();
            clearField();
            System.out.println(f1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        // Stage stage = (Stage) anchorFee.getScene().getWindow();stage.close();
    }

    public void lodeCourse() throws SQLException, ClassNotFoundException {
        List<String> courseId = new CourseSever().getAllCourseIds();
        cmbCourseId.getItems().addAll(courseId);

    }

    public void setCourseData(String courseData) throws SQLException, ClassNotFoundException {
        Course course = new CourseSever().passCourse(courseData);
        if (courseData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtCourseName.setText(course.getCourseName());
        }
    }

    public void setDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

    }

    public void clearField() {
        lblUpdateFeeId.setText("");
        txtCash.clear();
        txtCourseName.clear();
        //txtDate.clear();

    }

    public void btnClearDataOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       FeeTm fee = new FeeTm(
               lblUpdateFeeId.getText(),
               cmbCourseId.getValue(),
               Double.parseDouble(txtCash.getText()),
               lblDate.getText(),
               txtCourseName.getText()
        );

      if (new FeeSever().updateFee(fee)){
          setFeeId();
          //new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
          new AddNotificationForm().
                  sceneNotifications("Updated Fee Data ","Successfully Update Fee Data",0,6);
          setFeeTable();
          clearField();
      }else{
          new Alert(Alert.AlertType.WARNING, "Try Again").show();
      }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       FeeTm fee = tblFee.getSelectionModel().getSelectedItem();
        String feeId = fee.getFeeId();

        if (new FeeSever().deleteFee(feeId)){

            setFeeTable();
            //new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Delete Fee Data ","Successfully Deleted Fee Data",0,2);

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();
    }

    public void setFeeTable() throws SQLException, ClassNotFoundException {
        ObservableList<FeeTm> feeList = new FeeSever().getFeeList();

        colFeeId.setCellValueFactory(new PropertyValueFactory<>("feeId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCash.setCellValueFactory(new PropertyValueFactory<>("cash"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tblFee.setItems(feeList);

    }

    private void setFeeId() {

        try {
            lblFeeId.setText(new FeeSever().getFeeId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {

       FeeTm fee = null;

        fee = tblFee.getSelectionModel().getSelectedItem();
        lblUpdateFeeId.setText(""+fee.getFeeId());
        cmbCourseId.setValue(""+fee.getCourseId());
        txtCourseName.setText(""+fee.getCourseName());
        txtCash.setText(""+ fee.getCash());
       // txtDate.setText(""+fee.getDate());

    }

    public void btnFeeRelesed(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object respons = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER){
            TextField error = (TextField) respons;
            error.requestFocus();
        }else if (respons instanceof Boolean){
        }
    }
}