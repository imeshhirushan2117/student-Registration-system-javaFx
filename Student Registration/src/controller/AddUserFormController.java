package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import module.User;
import view.tm.UserTm;

import java.sql.SQLException;

public class AddUserFormController {
    public JFXTextField txtFistName;
    public JFXTextField txtLastName;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXTextField txtRole;

    public TableView<UserTm> tblAddUser;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colUserName;
    public TableColumn colRole;

    public void initialize() throws SQLException, ClassNotFoundException {
        setUserTable();
    }

    private void setUserTable() throws SQLException, ClassNotFoundException {

        ObservableList<UserTm> userList = new UserSever().getUserList();
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        tblAddUser.setItems(userList);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        UserTm user = tblAddUser.getSelectionModel().getSelectedItem();
        String userName = user.getUserName();

        if (new UserSever().deleteUser(userName)){
            setUserTable();
            //new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Delete User Data ","Successfully Deleted Users Data",0,2);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void clearField(){
        txtFistName.clear();
        txtLastName.clear();
        txtContact.clear();
        txtEmail.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtRole.clear();

        txtFistName.requestFocus();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        UserTm user = new UserTm(
                txtFistName.getText(),
                txtLastName.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtUserName.getText(),
                txtPassword.getText(),
                txtRole.getText()
        );

        if (new UserSever().updateUser(user)) {
            //new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            new AddNotificationForm().
                    sceneNotifications("Updated User Data ","Successfully Update Users Data",0,6);
            setUserTable();
            clearField();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        User u1 = new User(
                txtFistName.getText(),
                txtLastName.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtUserName.getText(),
                txtPassword.getText(),
                txtRole.getText()
        );

        if (new UserSever().saveUser(u1)){
            //new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            new AddNotificationForm().
                    sceneNotifications("User Saved Data","Successfully Saved Users Data",0,0);

            setUserTable();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {
        UserTm user = null;
        user = tblAddUser.getSelectionModel().getSelectedItem();
        txtFistName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        txtContact.setText(user.getContact());
        txtEmail.setText(user.getEmail());
        txtUserName.setText(user.getUserName());
        txtPassword.setText(user.getUserPassword());
        txtRole.setText(user.getRole());
    }
}
