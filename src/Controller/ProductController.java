package Controller;

import java.net.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class ProductController implements Initializable {

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
    private TextArea description;

    @FXML
    private TableView productsTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
