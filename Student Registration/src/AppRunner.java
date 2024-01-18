import com.sun.javafx.application.LauncherImpl;
import controller.IntPreLorderController;
import controller.LaunchPreLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppRunner extends Application {

        public static Stage primaryStage=null;
        public static Stage primaryScene=null;

        @Override
        public void init() throws Exception {
            IntPreLorderController init=new IntPreLorderController();
            init.checkFunction();
        }

        public static void main(String[] args) {

            //launch(args);
            LauncherImpl.launchApplication(AppRunner.class,LaunchPreLoader.class,args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            AppRunner.primaryStage = primaryStage;

        /*primaryStage.setScene(new Scene(load(getClass().getResource("view/loginForm.fxml"))));
        primaryStage.setTitle(" GREEN LIBRARY MANAGMENT SYSTEM");
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.show();*/
        }
    }
