package Controller;

import java.net.*;
import java.util.*;
import javafx.fxml.*;
import java.io.*;
import javafx.scene.*;
import javafx.scene.layout.VBox;

public class DashboardController implements Initializable {

    @FXML
    private VBox invoiceTab;
    @FXML
    private VBox billTab;
    @FXML
    private VBox productTab;
    @FXML
    private VBox distributerTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            invoiceTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Invoice.fxml")));
            billTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Bill.fxml")));
            productTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Product.fxml")));
            distributerTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Distributer.fxml")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
