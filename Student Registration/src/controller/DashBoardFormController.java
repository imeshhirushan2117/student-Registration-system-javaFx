package controller;

import com.sun.glass.ui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import sun.plugin2.os.windows.Windows;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public AnchorPane anchorDashBoard;
    public ToggleButton btnReport;

    public void btnAddUserOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../view/AddUserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        anchorDashBoard.getChildren().clear();
        anchorDashBoard.getChildren().add(load);

    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorDashBoard .getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void btnAddTeacherOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddTeacherForm.fxml");
        Parent load = FXMLLoader.load(resource);
        anchorDashBoard.getChildren().clear();
        anchorDashBoard.getChildren().add(load);

    }

    public void btnCourseManagementOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CourseManagementForm.fxml");
        Parent load = FXMLLoader.load(resource);
        anchorDashBoard.getChildren().clear();
        anchorDashBoard.getChildren().add(load);
    }

    public void btnAddSubjectOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddSubjectForm.fxml"));
        anchorDashBoard.getChildren().clear();
        anchorDashBoard.getChildren().add(load);
    }

    public void initialize(){
        btnReport.fire();
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddReportForm.fxml"));
        anchorDashBoard.getChildren().clear();
        anchorDashBoard.getChildren().add(load);
    }
}
