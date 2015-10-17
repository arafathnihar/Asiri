package Controller;

import Model.Product;
import Model.ProductModel;
import java.net.*;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private ComboBox<String> type;
    @FXML
    private TextArea description;
    @FXML
    private TextField minStock;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> idC;
    @FXML
    private TableColumn<Product, String> nameC;
    @FXML
    private TableColumn<Product, String> brandC;
    @FXML
    private TableColumn<Product, String> strengthC;
    @FXML
    private TableColumn<Product, String> typeC;

    ProductModel pm = new ProductModel();
    int index;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        brandC.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        strengthC.setCellValueFactory(new PropertyValueFactory<>("productStrength"));
        typeC.setCellValueFactory(new PropertyValueFactory<>("productType"));
        
        type.getItems().addAll("A", "B", "C", "D", "E");
        
        productTable.setItems(getProducts());
    }
    
    public ObservableList<Product> getProducts() {
        ObservableList<Product> products = pm.getDistributors();
        return products;
    }
    
    @FXML
    public void add() {
        Product p = new Product();
        p.setProductID(id.getText());
        p.setProductName(name.getText());
        p.setProductBrand(brand.getText());
        p.setProductDescription(description.getText());
        p.setProductStrength(Integer.parseInt(strength.getText()));
        p.setProductType(type.getValue());
        p.setProductMinStock(Integer.parseInt(minStock.getText()));
        productTable.getItems().add(p);
        pm.add(p);
        productTable.setItems(getProducts());
        clear();
    }

    @FXML
    public void search() {
    }

    @FXML
    public void edit() {
        index = productTable.getSelectionModel().getSelectedIndex();
        Product p = productTable.getItems().get(index);
        id.setText(p.getProductID());
        id.setDisable(true);
        name.setText(p.getProductName());
        brand.setText(p.getProductBrand());
        description.setText(p.getProductDescription());
        strength.setText(String.valueOf(p.getProductStrength()));
        type.setValue(p.getProductType());
        minStock.setText(String.valueOf(p.getProductMinStock()));
    }
    
    @FXML
    public void update() {
        Product p = new Product();
        p.setProductID(id.getText());
        id.setDisable(false);
        p.setProductName(name.getText());
        p.setProductBrand(brand.getText());
        p.setProductDescription(description.getText());
        p.setProductStrength(Integer.parseInt(strength.getText()));
        p.setProductType(type.getValue());
        p.setProductStock(p.getProductStock());
        p.setProductMinStock(Integer.parseInt(minStock.getText()));
        pm.update(p);
        productTable.setItems(getProducts());
        clear();
    }
    
    @FXML
    public void delete() {
        index = productTable.getSelectionModel().getSelectedIndex();
        pm.remove(productTable.getItems().get(index).getProductID());
        productTable.getItems().remove(index);
        productTable.setItems(getProducts());
        clear();
    }

    @FXML
    public void clear() {
        id.clear();
        name.clear();
        brand.clear();
        strength.clear();
        description.clear();
        minStock.clear();
    }

    @FXML
    public void cancel() {
    }

}
