/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author testing
 */
public class ProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField brand;
    @FXML
    private TextField strength;
    @FXML
    private ComboBox type;
    @FXML
    private TextArea discription;
    @FXML
    private Button add;
    @FXML
    private Button search;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;
    @FXML
    private Button clear;
    @FXML
    private TableView products;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
