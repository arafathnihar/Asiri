/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author testing
 */
public class InvoiceController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private TextField invoiceId;
    @FXML
    private ComboBox distributerCode;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea invoiceNote;
    @FXML
    private Button addNewDistributer;
    @FXML
    private Button addNewProduct;
    @FXML
    private Button save;
    
    @FXML
    private ComboBox productID;
    @FXML
    private TextField packSize;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private TextField discount;
    @FXML
    private TextField free;
    @FXML
    private TextField margin;
    @FXML
    private DatePicker expiery;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Button clear;
    @FXML
    private Button cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void addNewDistributer() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Distributer.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Distributer");
            stage.setScene(new Scene(root, 915, 581));
            stage.show();
        } catch (IOException e) {
        }
    }

    public void addNewProduc() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Product.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Product");
            stage.setScene(new Scene(root, 915, 581));
            stage.show();
        } catch (IOException e) {
        }
    }
}
