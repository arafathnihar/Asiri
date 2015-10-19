package Controller;

import Model.Product;
import Model.ProductModel;
import java.net.*;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ProductController implements Initializable {

	@FXML
	private TextField id;@FXML
	private TextField name;@FXML
	private TextField brand;@FXML
	private TextField strength;@FXML
	private ComboBox < String > type;@FXML
	private TextField minStock;@FXML
	private TextArea description;

	@FXML
	private Label idLabel;@FXML
	private Label nameLabel;@FXML
	private Label brandLabel;@FXML
	private Label strengthLabel;@FXML
	private Label typeLabel;@FXML
	private Label minStockLabel;@FXML
	private Label descriptionLabel;

	@FXML
	private TextField searchId;@FXML
	private TextField searchName;@FXML
	private TextField searchBrand;@FXML
	private TextField searchStrength;@FXML
	private ComboBox < String > searchType;@FXML
	private TextField searchMinStock;@FXML
	private TextArea searchDescription;

	@FXML
	private TableView < Product > productTable;

	@FXML
	private TableColumn < Product, String > idC;@FXML
	private TableColumn < Product, String > nameC;@FXML
	private TableColumn < Product, String > brandC;@FXML
	private TableColumn < Product, String > strengthC;@FXML
	private TableColumn < Product, String > typeC;@FXML
	private TableColumn < Product, String > minStockC;@FXML
	private TableColumn < Product, String > discriptionC;

	@FXML
	private ImageView icon;@FXML
	private Label messageLabel;@FXML
        private TitledPane discriptionPane;

	ProductModel pm = new ProductModel();

	int index;

	Image imageProduct = new Image(getClass().getResourceAsStream("/resource/images/product.png"));

	Image imageError = new Image(getClass().getResourceAsStream("/resource/images/error.png"));

	Image imageSuccess = new Image(getClass().getResourceAsStream("/resource/images/success.png"));

	Image imageWarnning = new Image(getClass().getResourceAsStream("/resource/images/warnning.png"));

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		idC.setCellValueFactory(new PropertyValueFactory < > ("productID"));
		nameC.setCellValueFactory(new PropertyValueFactory < > ("productName"));
		brandC.setCellValueFactory(new PropertyValueFactory < > ("productBrand"));
		strengthC.setCellValueFactory(new PropertyValueFactory < > ("productStrength"));
		typeC.setCellValueFactory(new PropertyValueFactory < > ("productType"));
		minStockC.setCellValueFactory(new PropertyValueFactory < > ("productMinStock"));
		discriptionC.setCellValueFactory(new PropertyValueFactory < > ("productDescription"));

		type.getItems().addAll("A", "B", "C", "D", "E");
		productTable.setItems(getProducts());
	}

	public ObservableList < Product > getProducts() {
		ObservableList < Product > products = pm.getDistributors();
		return products;
	}

	public boolean isValid() {

		if (id.getText().isEmpty()) {
			idLabel.setText("Requird !");
			return false;
		} else if (name.getText().isEmpty()) {
			nameLabel.setText("Requird !");
			return false;
		} else if (brand.getText().isEmpty()) {
			brandLabel.setText("Requird !");
			return false;
		} else if (strength.getText().isEmpty()) {
			strengthLabel.setText("Requird !");
			return false;
		} else if (type.getSelectionModel().isEmpty()) {
			typeLabel.setText("Requird !");
			return false;
		} else if (minStock.getText().isEmpty()) {
			minStockLabel.setText("Requird !");
			return false;
		} else {
			return true;
		}

	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	@FXML
	public void add() {
		if (isValid()) {

			if (pm.isExisting(id.getText()) <= 0) {
				if (isInteger(strength.getText())) {
					if (isInteger(minStock.getText())) {

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
                                                icon.setImage(imageSuccess);
                                                messageLabel.setTextFill(Color.GREEN);
                                                messageLabel.setText(" New product added ");
					} else {
                                                icon.setImage(imageError);
						minStockLabel.setText("It's not a number");
                                                messageLabel.setTextFill(Color.RED);
                                                messageLabel.setText(" Minimum stock should be a numaric value ");
					}
				} else {
                                        icon.setImage(imageError);
					strengthLabel.setText("It's not a number");
                                        messageLabel.setTextFill(Color.RED);
                                        messageLabel.setText(" Strength should be a numaric value ");
				}
			} else {
				icon.setImage(imageError);
				messageLabel.setTextFill(Color.RED);
				messageLabel.setText(" Product code allready Exist ");
				idLabel.setText("Existing code");
			}
		} else {
			icon.setImage(imageError);
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText(" Fill all fields ");
		}
	}
        
        @FXML
	public void idOnPress() {

		idLabel.setText("");
		icon.setImage(imageProduct);
		messageLabel.setText("");

	}

	@FXML
	public void nameOnPress() {

		nameLabel.setText("");
		icon.setImage(imageProduct);
		messageLabel.setText("");

	}

	@FXML
	public void brandOnPress() {

		brandLabel.setText("");
		icon.setImage(imageProduct);
		messageLabel.setText("");

	}

	@FXML
	public void strenthOnPress() {

		strengthLabel.setText("");
		icon.setImage(imageProduct);
		messageLabel.setText("");

	}

        @FXML
	public void typeOnPress() {

		typeLabel.setText("");
		icon.setImage(imageProduct);
		messageLabel.setText("");

	}
        
        @FXML
	public void minStockOnPress() {

		minStockLabel.setText("");
		icon.setImage(imageProduct);
		messageLabel.setText("");

	}
        
        
	@FXML
	public void expandDiscription() {
            discriptionPane.expandedProperty().set(true);
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
                type.getSelectionModel().clearSelection();
                
	}

	@FXML
	public void cancel() {
        }

}