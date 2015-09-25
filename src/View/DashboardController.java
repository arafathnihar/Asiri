/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author testing
 */

public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private VBox vbx;
    private Button btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void testing() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Invoice.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Innvoice");
            stage.setScene(new Scene(root, 915, 581));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
