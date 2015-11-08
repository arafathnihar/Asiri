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
    private TableColumn< Product, String> outProductIDC;
    @FXML
    private TableColumn< Product, String> outProductNameC;
    @FXML
    private TableColumn< Product, String> outCurrentStockC;
    @FXML
    private TableColumn< Product, String> outMinStockC;
    @FXML
    private TableView< Product> expStockTable;
    @FXML
    private TableColumn< Product, String> expProductIDC;
    @FXML
    private TableColumn< Product, String> expProductNameC;
    @FXML
    private TableColumn< Product, String> expQuantityC;
    @FXML
    private TableColumn< Product, String> expStockDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        outProductIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        outProductNameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        outCurrentStockC.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        outMinStockC.setCellValueFactory(new PropertyValueFactory<>("productMinStock"));

        expProductIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        expProductNameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        expQuantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        expStockDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));

        NotificationModel nm = new NotificationModel();
        outOfStockTable.setItems(nm.minStockNotification());
        expStockTable.setItems(nm.expireNotification());
    }

    public TableView<Product> getOutOfStockTable() {
        return outOfStockTable;
    }

    public void setOutOfStockTable(TableView<Product> outOfStockTable) {
        this.outOfStockTable = outOfStockTable;
    }

    public TableView<Product> getExpStockTable() {
        return expStockTable;
    }

    public void setExpStockTable(TableView<Product> expStockTable) {
        this.expStockTable = expStockTable;
    }
}
