package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author testing
 */
public class Asiri extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resource/Dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image i = new Image("/resource/images/logo.png");
        stage.getIcons().setAll(i);
        stage.setTitle("ASIRI PHARMACY");
        //stage.setFullScreen(true);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
