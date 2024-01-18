package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class AddNotificationForm {
    public void sceneNotifications(String title, String txt, int showMode, int image) {

        Image img;
        switch (image) {
            case 1:
                img = new Image("/assets/icon/cancel.png");
                break;
            case 2:
                img = new Image("/assets/icon/delete.png");
                break;
            case 3:
                img = new Image("/assets/icon/error.png");
                break;
            case 4:
                img = new Image("/assets/icon/lock.png.");
                break;
            case 5:
                img = new Image("/assets/icon/unLock.png");
                break;
            case 6:
                img = new Image("/assets/icon/updated.png");
                break;

            default:
                img = new Image("/assets/icon/done.png");
                break;
        }

        Notifications notificationBuilder = Notifications.create();
        notificationBuilder.title(title)
                .text(txt)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });

        switch (showMode) {
            case 1:
                notificationBuilder.showConfirm();
                break;
            case 2:
                notificationBuilder.showInformation();
                break;
            case 3:
                notificationBuilder.showError();
                break;
            case 4:
                //notificationBuilder.darkStyle();
                notificationBuilder.showWarning();
                break;
            default:
                //notificationBuilder.darkStyle();
                notificationBuilder.show();
                break;
        }

    }
}
