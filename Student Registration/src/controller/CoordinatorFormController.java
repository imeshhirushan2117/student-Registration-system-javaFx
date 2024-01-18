package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CoordinatorFormController {
    public AnchorPane anchorCoordinator;
    public ToggleButton btnDashBoard;


    public void initialize(){
        btnDashBoard.fire();
    }
    public void btnAddStudentOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../view/AddStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        anchorCoordinator.getChildren().clear();
        anchorCoordinator.getChildren().add(load);

    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorCoordinator .getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/CoordinatorDashBoardForm.fxml"));
        anchorCoordinator.getChildren().clear();
        anchorCoordinator.getChildren().add(load);
    }
}
