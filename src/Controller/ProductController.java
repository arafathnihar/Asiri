package Controller;

import Model.Product;
import Model.ProductModel;
import java.net.*;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	private TextField minStock;@FXML
	private TextField searchId;@FXML
	private TextField searchName;@FXML
	private TextField searchBrand;@FXML
	private TextField searchStrength;@FXML
	private TextField searchMinStock;@FXML
	private TextField searchDescription;

	@FXML
	private TextArea description;

	@FXML
	private Label idLabel;@FXML
	private Label nameLabel;@FXML
	private Label brandLabel;@FXML
	private Label strengthLabel;@FXML
	private Label typeLabel;@FXML
	private Label minStockLabel;@FXML
	private Label descriptionLabel;@FXML
	private Label messageLabel;

	@FXML
	private ComboBox < String > searchType;@FXML
	private ComboBox < String > type;

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
	private TitledPane discriptionPane;@FXML
	private Button addBtn;

	int index;
	ProductModel pm = new ProductModel();

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

		type.getItems().addAll("one", "two", "three", "four", "five","six","seven","eight","nine","ten");
		searchType.getItems().addAll("one", "two", "three", "four", "five","six","seven","eight","nine","ten");
		refreshProducts();
	}



	public void refreshProducts() {
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList < Product > filteredData = new FilteredList < > (getProducts(), p -> true);


		// 2. Set the filter Predicate whenever the filter changes.
		searchId.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (product.getProductID().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});

		});

		// 2. Set the filter Predicate whenever the filter changes.
		searchName.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

		// 2. Set the filter Predicate whenever the filter changes.
		searchBrand.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (product.getProductBrand().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

		// 2. Set the filter Predicate whenever the filter changes.
		searchStrength.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(product.getProductStrength()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

		// 2. Set the filter Predicate whenever the filter changes.
		searchType.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (product.getProductType().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});
		// 2. Set the filter Predicate whenever the filter changes.
		searchMinStock.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(product.getProductMinStock()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});

		// 2. Set the filter Predicate whenever the filter changes.
		searchDescription.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare id of every item with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (product.getProductDescription().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
		});
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList < Product > sortedData = new SortedList < > (filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(productTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		productTable.setItems(sortedData);
                
                // bind the sortedList comparator to the TableView comparator
               sortedData.comparatorProperty().bind(productTable.comparatorProperty());

	}

	public ObservableList < Product > getProducts() {
		ObservableList < Product > products = pm.getDistributors();
		Collections.reverse(products);
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

		if (id.isDisable()) update();
		else addNew();
	}

	@FXML
	public void addNew() {
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
						pm.add(p);
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
	public void update() {
		if (isValid()) {
			if (isInteger(strength.getText())) {
				if (isInteger(minStock.getText())) {


					Product p = new Product();
					p.setProductID(id.getText());
					p.setProductName(name.getText());
					p.setProductBrand(brand.getText());
					p.setProductDescription(description.getText());
					p.setProductStrength(Integer.parseInt(strength.getText()));
					p.setProductType(type.getValue());
					p.setProductStock(p.getProductStock());
					p.setProductMinStock(Integer.parseInt(minStock.getText()));
					pm.update(p);
					clear();
					icon.setImage(imageSuccess);
					messageLabel.setTextFill(Color.GREEN);
					messageLabel.setText(" The Product is updated ");
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
			messageLabel.setText(" Fill all fields ");
		}
	}

	@FXML
	public void edit() {

		index = productTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			Product p = productTable.getItems().get(index);
			id.setText(p.getProductID());
			id.setDisable(true);
			name.setText(p.getProductName());
			brand.setText(p.getProductBrand());
			description.setText(p.getProductDescription());
			strength.setText(String.valueOf(p.getProductStrength()));
			type.setValue(p.getProductType());
			minStock.setText(String.valueOf(p.getProductMinStock()));
			addBtn.setText("Update");
			icon.setImage(imageProduct);
			messageLabel.setText("");
		} else {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("Please select a Product to edit");
			icon.setImage(imageWarnning);
			messageLabel.setTextFill(Color.ORANGE);
			messageLabel.setText("Please select a Product to edit");
		}

	}

	@FXML
	public void delete() {
		index = productTable.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			pm.remove(productTable.getItems().get(index).getProductID());
			clear();
			icon.setImage(imageSuccess);
			messageLabel.setTextFill(Color.GREEN);
			messageLabel.setText(" The Product is Deleted ");
		} else {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("Please select a Product to delete");
			icon.setImage(imageWarnning);
			messageLabel.setTextFill(Color.ORANGE);
			messageLabel.setText("Please select a Product to delete");
		}
	}

	@FXML
	public void clearSearch() {
		if (!searchId.isFocused()) searchId.clear();
		if (!searchName.isFocused()) searchName.clear();
		if (!searchBrand.isFocused()) searchBrand.clear();
		if (!searchStrength.isFocused()) searchStrength.clear();
		if (!searchType.isFocused()) searchType.getSelectionModel().clearSelection();
		if (!searchMinStock.isFocused()) searchMinStock.clear();
		if (!searchDescription.isFocused()) searchDescription.clear();
	}

	public void clear() {

		id.clear();
		name.clear();
		brand.clear();
		strength.clear();
		type.getSelectionModel().clearSelection();
		minStock.clear();
		description.clear();


		idLabel.setText("");
		nameLabel.setText("");
		brandLabel.setText("");
		strengthLabel.setText("");
		typeLabel.setText("");
		minStockLabel.setText("");
		descriptionLabel.setText("");


		searchId.clear();
		searchName.clear();
		searchBrand.clear();
		searchStrength.clear();
		searchType.getSelectionModel().clearSelection();
		searchMinStock.clear();
		searchDescription.clear();
		id.setDisable(false);
		addBtn.setText("Add");
		discriptionPane.expandedProperty().set(false);
		refreshProducts();
	}

	@FXML
	public void clearAll() {
		clear();
                onPressAnything();

	}

	@FXML
	public void idOnPress() {

		idLabel.setText("");
                onPressAnything();

	}

	@FXML
	public void nameOnPress() {

		nameLabel.setText("");
                onPressAnything();

	}

	@FXML
	public void brandOnPress() {

		brandLabel.setText("");
                onPressAnything();

	}

	@FXML
	public void strenthOnPress() {

		strengthLabel.setText("");
                onPressAnything();

	}

	@FXML
	public void typeOnPress() {

		typeLabel.setText("");
                onPressAnything();

	}

	@FXML
	public void minStockOnPress() {

		minStockLabel.setText("");
                onPressAnything();

	}
        
        @FXML
	public void onPressAnything() {

		icon.setImage(imageProduct);
		messageLabel.setText("");
	}

	@FXML
	public void expandDiscription() {
		discriptionPane.expandedProperty().set(true);
	}


}