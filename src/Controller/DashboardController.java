package Controller;

import java.net.*;
import java.util.*;
import javafx.fxml.*;
import java.io.*;
import javafx.scene.*;
import javafx.stage.*;

public class DashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public void testing() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Invoice.fxml"));
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root, 915, 580));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
