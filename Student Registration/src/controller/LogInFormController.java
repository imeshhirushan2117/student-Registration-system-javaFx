package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import module.User;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LogInFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane anchorLogIn;
    public JFXButton btnSignIn;

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        User u = new User(txtUserName.getText(), txtPassword.getText());

        String role = new UserSever().logIn(u);

        if (role.equals("admin")){
            Stage window = (Stage) anchorLogIn.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
            window.centerOnScreen();
            window.setResizable(false);
            new AddNotificationForm().
                    sceneNotifications("Success login Admin","Admin Login successful..",0,5);

        }else if (role.equals("user")) {
            System.out.println("User logIn");


            Stage window = (Stage) anchorLogIn.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CoordinatorForm.fxml"))));
            window.centerOnScreen();
//            URL resource = getClass().getResource("../view/CoordinatorForm.fxml");
//            URL resource = getClass().getResource("/view/CoordinatorForm.fxml");
//            Parent load = FXMLLoader.load(resource);
//            Stage window = (Stage) anchorLogIn.getScene().getWindow();
//            window.setScene(new Scene(load));
            window.centerOnScreen();
            window.setResizable(false);
            new AddNotificationForm().
                    sceneNotifications("Success login User","User Login successful..",0,5);

        }else{
           // new Alert(Alert.AlertType.WARNING,"Something went wrong Please try again..").show();
            new AddNotificationForm().
                    sceneNotifications("Failed login","Something went wrong Please try again..",0,4);
        }
    }
}