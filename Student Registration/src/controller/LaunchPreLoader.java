package controller;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchPreLoader extends Preloader {
        private Stage preLoarderStage;

        @Override
        public void start(Stage primaryStage) throws Exception {
            this.preLoarderStage=primaryStage;
           Parent root = FXMLLoader.load(getClass().getResource("../view/IntPreLorder.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        }

        @Override
        public void handleStateChangeNotification(StateChangeNotification info) {
            if (info.getType()== StateChangeNotification.Type.BEFORE_START){
                preLoarderStage.hide();
            }
        }
    }
