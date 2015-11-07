package Controller;

import Model.Invoice;
import Model.InvoiceItem;
import Model.InvoiceItemModel;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InvoiceController implements Initializable {

    @FXML
    private TextField invoiceID;
    @FXML
    private ComboBox< String> distributerCode;
    @FXML
    private DatePicker date;
    @FXML
    private TextField invoiceNote;
    @FXML
    private ComboBox< String> productID;
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
    private DatePicker expireDate;
    @FXML
    private TableView< InvoiceItem> invoiceItemTable;
    @FXML
    private TableColumn< InvoiceItem, String> productIDC;
    @FXML
    private TableColumn< InvoiceItem, Integer> packSizeC;
    @FXML
    private TableColumn< InvoiceItem, Integer> quantityC;
    @FXML
    private TableColumn< InvoiceItem, Double> priceC;
    @FXML
    private TableColumn< InvoiceItem, Double> discountC;
    @FXML
    private TableColumn< InvoiceItem, Integer> freeC;
    @FXML
    private TableColumn< InvoiceItem, Integer> marginC;
    @FXML
    private TableColumn< InvoiceItem, Date> expireDateC;
    @FXML
    private Label invoiceIdLabel;
    @FXML
    private Label distriCodeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label noteLabel;
    @FXML
    private Label productIdLabel;
    @FXML
    private Label packSizeLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label discountLabel;
    @FXML
    private Label freeLabel;
    @FXML
    private Label marginLabel;
    @FXML
    private Label expireDateLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private ComboBox<String> searchProductID;
    @FXML
    private TextField searchPackSize;
    @FXML
    private TextField searchQuantity;
    @FXML
    private TextField searchPrice;
    @FXML
    private TextField searchDiscount;
    @FXML
    private TextField searchFree;
    @FXML
    private TextField searchMargin;
    @FXML
    private DatePicker searchExpireDate;

    InvoiceItemModel iim = new InvoiceItemModel();
    int index;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        packSizeC.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        discountC.setCellValueFactory(new PropertyValueFactory<>("discount"));
        freeC.setCellValueFactory(new PropertyValueFactory<>("free"));
        marginC.setCellValueFactory(new PropertyValueFactory<>("margin"));
        expireDateC.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        distributerCode.setItems(getDistributerCode());
        productID.setItems(getProductsID());
       // searchProductID.setItems(getProductsID());
     //   refreshInvoiceItems();
    }
/*
    public void refreshInvoiceItems(){
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList< InvoiceItem> filteredData = new FilteredList<>( invoiceItems, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        searchProductID.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (invoiceItem.getProductID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        searchPackSize.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getPackSize()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        searchQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getQuantity()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        searchPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        searchDiscount.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getDiscount()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        
        // 2. Set the filter Predicate whenever the filter changes.
        searchFree.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getFree()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        
        // 2. Set the filter Predicate whenever the filter changes.
        searchMargin.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getMargin()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        
        // 2. Set the filter Predicate whenever the filter changes.
        searchExpireDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoiceItem -> {
                // If filter text is empty, display all items.
                if (newValue == null) {
                    return true;
                }
                // Compare id of every item with filter text.
               // String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(invoiceItem.getMargin()).contains(newValue.toString())) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        
         // 3. Wrap the FilteredList in a SortedList. 
        SortedList< InvoiceItem> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(invoiceItemTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        invoiceItemTable.setItems(sortedData);
        // bind the sortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(invoiceItemTable.comparatorProperty());

       }
   */
    
    public ObservableList< String> getProductsID() {
        ObservableList< String> products = iim.getProductID();
        return products;
    }

    public ObservableList< String> getDistributerCode() {
        ObservableList< String> distributers = iim.getDistributerCode();
        return distributers;
    }

    @FXML
    public void add() {
        if (productID.isDisable()) {
            update();
        } else {
            addNew();
        }
    }

    @FXML
    public void addNew() {
        InvoiceItem ii = new InvoiceItem();
        ii.setProductID(productID.getValue());
        ii.setPackSize(Integer.parseInt(packSize.getText()));
        ii.setQuantity(Integer.parseInt(quantity.getText()));
        ii.setFree(Integer.parseInt(free.getText()));
        ii.setPrice(Double.parseDouble(price.getText()));
        ii.setMargin(Integer.parseInt(margin.getText()));
        ii.setExpireDate(expireDate.getValue());
        ii.setDiscount(Double.parseDouble(discount.getText()));
        invoiceItemTable.getItems().add(ii);
        clear();
    }
    
    @FXML
    public void save() {
        Invoice i = new Invoice();
        i.setInvoiceID(invoiceID.getText());
        i.setDistibutorCode(distributerCode.getValue());
        i.setInvoiceDate(java.sql.Date.valueOf(date.getValue()));
        i.setInvoiceNote(invoiceNote.getText());
        ObservableList< InvoiceItem> items = invoiceItemTable.getItems();
        int j = 1;
        for (int k = 0; k < items.size(); k++) {
            items.get(k).setInvoiceID(invoiceID.getText());
            items.get(k).setItemID("Item-" + j);
            j++;
        }
        i.setItems(items);
        iim.addInvoice(i);
        cancel();       
    }

    @FXML
    public void edit() {
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        InvoiceItem ii = invoiceItemTable.getItems().get(index);
        productID.setValue(ii.getProductID());
        packSize.setText(String.valueOf(ii.getPackSize()));
        quantity.setText(String.valueOf(ii.getQuantity()));
        free.setText(String.valueOf(ii.getFree()));
        price.setText(String.valueOf(ii.getPrice()));
        margin.setText(String.valueOf(ii.getMargin()));
        expireDate.setValue(ii.getExpireDate());
        discount.setText(String.valueOf(ii.getDiscount()));
    }

    @FXML
    public void update() {
        InvoiceItem ii = new InvoiceItem();
        ii.setProductID(productID.getValue());
        ii.setPackSize(Integer.parseInt(packSize.getText()));
        ii.setQuantity(Integer.parseInt(quantity.getText()));
        ii.setFree(Integer.parseInt(free.getText()));
        ii.setPrice(Double.parseDouble(price.getText()));
        ii.setMargin(Integer.parseInt(margin.getText()));
        ii.setExpireDate(expireDate.getValue());
        ii.setDiscount(Double.parseDouble(discount.getText()));
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        invoiceItemTable.getItems().set(index, ii);
        clear();
    }

    @FXML
    public void delete() {
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        invoiceItemTable.getItems().remove(index);
        clear();
    }

    @FXML
    public void clear() {
        productID.setValue(null);
        packSize.clear();
        quantity.clear();
        price.clear();
        discount.clear();
        free.clear();
        margin.clear();
        expireDate.setValue(null);
        searchProductID.setValue(null);
        searchPackSize.clear();
        searchQuantity.clear();
        searchPrice.clear();
        searchDiscount.clear();
        searchFree.clear();
        searchMargin.clear();
        searchExpireDate.setValue(null);
        
    }

    @FXML
    public void cancel() {
        invoiceID.clear();
        distributerCode.setValue(null);
        date.setValue(null);
        invoiceNote.clear();
        invoiceItemTable.getItems().clear();
    }

}
