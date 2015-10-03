package Controller;

import java.net.*;
import java.util.*;
import javafx.fxml.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane invoiceTab;
    @FXML
    private AnchorPane billTab;
    @FXML
    private AnchorPane productTab;
    @FXML
    private AnchorPane distributerTab;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           invoiceTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Invoice.fxml")));
           billTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Bill.fxml"))); 
           productTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Product.fxml")));
           distributerTab.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Distributer.fxml")));
       } catch (IOException ex) {
           
        }
    }
}