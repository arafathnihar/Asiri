package Controller;

import Model.DTO.Invoice;
import Model.DTO.InvoiceItem;
import Model.Service.InvoiceItemModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class InvoiceController implements Initializable {

    @FXML
    private TextField invoiceID;
    @FXML
    private ComboBox<String> distributerCode;
    @FXML
    private DatePicker date;
    @FXML
    private TextField invoiceNote;
    @FXML
    private TextField invoiceTotal;
    @FXML
    private ComboBox<String> productID;
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
    private TableView<InvoiceItem> invoiceItemTable;
    @FXML
    private TableColumn<InvoiceItem, String> productIDC;
    @FXML
    private TableColumn<InvoiceItem, Integer> packSizeC;
    @FXML
    private TableColumn<InvoiceItem, Integer> quantityC;
    @FXML
    private TableColumn<InvoiceItem, Double> priceC;
    @FXML
    private TableColumn<InvoiceItem, Double> discountC;
    @FXML
    private TableColumn<InvoiceItem, Integer> freeC;
    @FXML
    private TableColumn<InvoiceItem, Double> marginC;
    @FXML
    private TableColumn<InvoiceItem, Date> expireDateC;
    @FXML
    private Label distriCodeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label noteLabel;
    @FXML
    private Label productIDLabel;
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
    private Button addBtn;
    
    Alert alert;

    InvoiceItemModel iim = new InvoiceItemModel();
    int index;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invoiceID.setText(String.valueOf(getInvoiceId()));
        invoiceID.setDisable(true);
        invoiceTotal.setDisable(true);
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        packSizeC.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        discountC.setCellValueFactory(new PropertyValueFactory<>("discount"));
        freeC.setCellValueFactory(new PropertyValueFactory<>("free"));
        marginC.setCellValueFactory(new PropertyValueFactory<>("margin"));
        expireDateC.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        date.setValue(LocalDate.now());
        distributerCode.setItems(getDistributerCode());
        productID.setItems(getProductsID());
    }

    public int getInvoiceId(){
        return iim.getInvoiceId();
    }
    
    public boolean isValid() {
        if (productID.getSelectionModel().isEmpty()) {
            productIDLabel.setText("Required !");
            return false;
        } else if (quantity.getText().isEmpty()) {
            quantityLabel.setText("Required !");
            return false;
        } else if (price.getText().isEmpty()) {
            priceLabel.setText("Required !");
            return false;
        }
        else if(expireDate.getValue() == null){
            expireDateLabel.setText("Required !");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isValidinvoice() {
        if (distributerCode.getSelectionModel().isEmpty()) {
            distriCodeLabel.setText("Required!");
            return false;
        } else if (date.getValue() == null) {
            dateLabel.setText("Required!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @FXML
    public void add() {
        if (productID.isDisable()) {
            update();
        } else {
            addNew();
        }
    }
    
    public ObservableList< String> getProductsID() {
        ObservableList< String> products = iim.getProductID();
        return products;
    }

    public ObservableList< String> getDistributerCode() {
        ObservableList< String> distributers = iim.getDistributerCode();
        return distributers;
    }
    
    @FXML
    public void addNew() {
        if (isValid()) {
            if (isInteger(quantity.getText())) {
                if (isDouble(price.getText())) {
                    InvoiceItem ii = new InvoiceItem();
                    ii.setProductID(productID.getValue());
                    if(!packSize.getText().equals("")){
                        if(isInteger(packSize.getText()))
                            ii.setPackSize(Integer.parseInt(packSize.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Pack Size should be a numeric value ");
                            return;
                        }
                    }
                    ii.setQuantity(Integer.parseInt(quantity.getText()));
                    if(!free.getText().equals("")){
                        if(isInteger(free.getText()))
                            ii.setFree(Integer.parseInt(free.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Free should be a numeric value ");
                            return;
                        }
                    }
                    ii.setPrice(Double.parseDouble(price.getText()));
                    if(!margin.getText().equals("")){
                        if(isDouble(margin.getText()))
                            ii.setMargin(Double.parseDouble(margin.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Margin should be a decimal value ");
                            return;
                        }
                    }
                    ii.setExpireDate(expireDate.getValue());
                    if(!discount.getText().equals("")){
                        if(isDouble(discount.getText()))
                            ii.setDiscount(Double.parseDouble(discount.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Discount should be a decimal value ");
                            return;
                        }
                    }
                    invoiceItemTable.getItems().add(ii);
                    clearInvoiceItemItemFields();
                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setText(" New product added to the invoice ");
                } else {
                    priceLabel.setText("It's not a decimal");
                    messageLabel.setTextFill(Color.RED);
                    messageLabel.setText(" Price should be a decimal value ");
                }
            } else {
                quantityLabel.setText("It's not a number");
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Quantity should be a numeric value ");
             }
        } 
        else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }
    }
    
    @FXML
    public void save() {
        if(invoiceItemTable.getItems().size() == 0){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add invoice items before save the invoice");
            alert.showAndWait();
        }
        else {
            if(isValidinvoice()) {
                Invoice i = new Invoice();
                i.setInvoiceID(getInvoiceId());
                i.setDistibutorCode(distributerCode.getValue());
                i.setInvoiceDate(date.getValue());
                if(!invoiceNote.getText().equals(""))
                    i.setInvoiceNote(invoiceNote.getText());
                ObservableList< InvoiceItem> items = invoiceItemTable.getItems();         
                iim.addInvoice(i, items);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Invoice added with "+ invoiceItemTable.getItems().size() + " items" );
                alert.showAndWait();
                clearAll();
            }
            else {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Fill all fields ");
            }
        }
    }
    
    @FXML
    public void edit() {
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            InvoiceItem ii = invoiceItemTable.getItems().get(index);
            productID.setValue(ii.getProductID());
            packSize.setText(String.valueOf(ii.getPackSize()));
            quantity.setText(String.valueOf(ii.getQuantity()));
            free.setText(String.valueOf(ii.getFree()));
            price.setText(String.valueOf(ii.getPrice()));
            margin.setText(String.valueOf(ii.getMargin()));
            expireDate.setValue(ii.getExpireDate());
            discount.setText(String.valueOf(ii.getDiscount()));
            productID.setDisable(true);
            addBtn.setText("Update");
            messageLabel.setText("");
        } else {
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText(" Please select an invoice item to edit ");
        }
    }
    
    @FXML
    public void update() {
        if (isValid()) {
            if (isInteger(quantity.getText())) {
                if (isDouble(price.getText())) {
                    InvoiceItem ii = new InvoiceItem();
                    ii.setProductID(productID.getValue());
                    if(!packSize.getText().equals("")){
                        if(isInteger(packSize.getText()))
                            ii.setPackSize(Integer.parseInt(packSize.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Pack Size should be a numeric value ");
                            return;
                        }
                    }
                    ii.setQuantity(Integer.parseInt(quantity.getText()));
                    if(!free.getText().equals("")){
                        if(isInteger(free.getText()))
                            ii.setFree(Integer.parseInt(free.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Free should be a numeric value ");
                            return;
                        }
                    }
                    ii.setPrice(Double.parseDouble(price.getText()));
                    if(!margin.getText().equals("")){
                        if(isDouble(margin.getText()))
                            ii.setMargin(Double.parseDouble(margin.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Margin should be a decimal value ");
                            return;
                        }
                    }
                    ii.setExpireDate(expireDate.getValue());
                    if(!discount.getText().equals("")){
                        if(isDouble(discount.getText()))
                            ii.setDiscount(Double.parseDouble(discount.getText()));
                        else{
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Discount should be a decimal value ");
                            return;
                        }
                    }
                    index = invoiceItemTable.getSelectionModel().getSelectedIndex();
                    invoiceItemTable.getItems().set(index, ii);
                    clearInvoiceItemItemFields();
                    productID.setDisable(false);
                    addBtn.setText("Add");
                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setText(" Invoice item updated ");
                } else {
                    priceLabel.setText("It's not a decimal");
                    messageLabel.setTextFill(Color.RED);
                    messageLabel.setText(" Price should be a decimal value ");
                }
            } else {
                quantityLabel.setText("It's not a number");
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Quantity should be a numeric value ");
            }
        } else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }
    }

    @FXML
    public void delete() {
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Conformation");
            alert.setHeaderText("Are you sure to delete selected invoice item?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                invoiceItemTable.getItems().remove(index);
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("The Product item is Deleted");
            } else if (result.get() == ButtonType.CANCEL){
                alert.close();
            }
        } else {
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText("Please select an item to delete");
        }
    }
    
    public void clearInvoiceItemItemFields(){
        productID.setValue(null);
        packSize.clear();
        quantity.clear();
        price.clear();
        discount.clear();
        free.clear();
        margin.clear();
        expireDate.setValue(null);
    }   
    
    public void clearInvoiceFields(){
        distributerCode.setValue(null);
        date.setValue(null);
        invoiceNote.clear();
        invoiceTotal.clear();   
    }
    
    @FXML
    public void clear() {
        clearInvoiceFields();
        clearInvoiceItemItemFields();
    }

    @FXML
    public void cancel() {
        clearInvoiceFields();
        clearInvoiceItemItemFields();
        invoiceItemTable.getItems().clear();
    }
    
    public void clearAll(){
        cancel();
    }
     
    @FXML
    public void distributorOnPress(){
        distriCodeLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void dateOnPress() {
        dateLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void noteOnPress() {
        noteLabel.setText("");
        onPressAnything();
    }
  
    @FXML
    public void productIDOnPress() {
        productIDLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void packSizeOnPress() {
        packSizeLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void quantityOnPress() {
        quantityLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void priceOnPress() {
        priceLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void discountOnPress() {
        discountLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void freeOnPress() {
        freeLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void marginOnPress() {
        marginLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void expireOnPress() {
        expireDateLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void onPressAnything() {
        messageLabel.setText("");
    }

}
