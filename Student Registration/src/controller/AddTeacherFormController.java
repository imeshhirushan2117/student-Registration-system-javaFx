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
import module.Teacher;
import util.Validation;
import view.tm.TeacherTm;
import view.tm.UserTm;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddTeacherFormController {

    public TableView<TeacherTm> tblAddTeacher;

    public TableColumn colEmail;
    //public JFXTextField txtTeacherId;
    public JFXTextField txtTeacherName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
   // public JFXTextField txtGender;
    public TableColumn colTeacherId;
    public TableColumn colTeacherName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colGender;
    public Label lblUpdateTeacherId;
    public Label lblTeacherId;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnAdd;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();

    //================================
    Pattern teacherName = Pattern.compile("^[A-Z][a-z]{3,} [A-Z][a-z]{3,}$");
    Pattern address = Pattern.compile("^[A-Z][a-z]{3,}$");
    Pattern contact= Pattern.compile("^(077|075|072|076|071|038)[-][0-9]{7}$");
    Pattern email = Pattern.compile("^[a-z0-9]{2,}[@][a-z]{5}(.)[a-z]{3}$");

    public void initialize() throws SQLException, ClassNotFoundException {

        btnAdd.setDisable(true);
        setTeacherId();
        setTeacherTable();
        storeValidation();
        cmbGender.getItems().setAll("Male","Female");
    }

    private void storeValidation(){
        map.put(txtTeacherName,teacherName);
        map.put(txtAddress,address);
        map.put(txtContact,contact);
        map.put(txtEmail,email);
    }

    private void setTeacherTable() throws SQLException, ClassNotFoundException {

        ObservableList<TeacherTm> teacherList = new TeacherSever().getTeacherList();
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("teacherAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("teacherContact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("teacherEmail"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblAddTeacher.setItems(teacherList);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Teacher t1 = new Teacher(
                lblTeacherId.getText(),
                txtTeacherName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                cmbGender.getValue()
        );
        if (new TeacherSever().saveTeacher(t1)) {
            // new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            setTeacherId();
            new AddNotificationForm().
                    sceneNotifications("Saved Teacher Data", "Successfully Saved Teachers Data", 0, 0);
            setTeacherTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        TeacherTm teacher = tblAddTeacher.getSelectionModel().getSelectedItem();
        String teacherId = teacher.getTeacherId();

        if (new TeacherSever().deleteTeacher(teacherId)) {
            setTeacherId();
            setTeacherTable();
            //  new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Delete Teacher Data ", "Successfully Deleted Teachers Data", 0, 2);


        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void clearField() {
        lblUpdateTeacherId.setText("");
        txtTeacherName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        //txtGender.clear();

        txtTeacherName.requestFocus();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        TeacherTm teacher = new TeacherTm(
                lblUpdateTeacherId.getText(),
                txtTeacherName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                cmbGender.getValue()
        );

        if (new TeacherSever().updateTeacher(teacher)) {
            //new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            new AddNotificationForm().
                    sceneNotifications("Updated Teacher Data ", "Successfully Update Teachers Data", 0, 6);
            setTeacherId();
            setTeacherTable();
            clearField();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }

    }

    private void setTeacherId() {
        try {
            lblTeacherId.setText(new TeacherSever().getTeacherId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {

        TeacherTm teacher = null;
        teacher =  tblAddTeacher.getSelectionModel().getSelectedItem();

        lblUpdateTeacherId.setText(teacher.getTeacherId());
        txtTeacherName.setText(teacher.getTeacherName());
        txtAddress.setText(teacher.getTeacherAddress());
        txtContact.setText(teacher.getTeacherContact());
        txtEmail.setText(teacher.getTeacherEmail());
        cmbGender.setValue(teacher.getGender());
    }


    public void btnTeacherRelease(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField error = (TextField) response;
            error.requestFocus();
        } else if (response instanceof Boolean) {
        }
    }
}
