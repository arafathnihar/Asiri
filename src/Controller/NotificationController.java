package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.NotificationModel;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author testing
 */
public class NotificationController implements Initializable {

    @FXML
    private TableView< Product> outOfStockTable;
    @FXML
    private TableColumn< Product, String> productIDC;
    @FXML
    private TableColumn< Product, String> productNameC;
    @FXML
    private TableColumn< Product, String> currentStockC;
    @FXML
    private TableColumn< Product, String> minStockC;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        currentStockC.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        minStockC.setCellValueFactory(new PropertyValueFactory<>("productMinStock"));
        NotificationModel nm = new NotificationModel();
        outOfStockTable.setItems(nm.minStockNotification());
    }    
    
    public TableView<Product> getOutOfStockTable() {
        return outOfStockTable;
    }

    public void setOutOfStockTable(TableView<Product> outOfStockTable) {
        this.outOfStockTable = outOfStockTable;
    }
}
