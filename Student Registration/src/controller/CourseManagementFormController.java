package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseManagementFormController {
    public void btnAddCourseOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddCourseForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnAddBatchOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddBatchForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnAddFeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddFeeForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnAddExamOnAction(ActionEvent actionEvent) throws IOException {
         Parent load = FXMLLoader.load(getClass().getResource("../view/AddExamForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
