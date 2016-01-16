package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Asiri extends Application {

    @Override
    public void start(Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Dashboard.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Asiri.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image i = new Image("/resource/images/logo.png");
        stage.getIcons().setAll(i);
        stage.setTitle("ASIRI PHARMACY");
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
