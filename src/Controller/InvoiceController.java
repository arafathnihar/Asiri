package Controller;

import Model.Invoice;
import Model.InvoiceItem;
import Model.InvoiceItemModel;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InvoiceController implements Initializable {

    @FXML
    private TextField invoiceID;
    @FXML
    private ComboBox<String> distributerCode;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea invoiceNote;
    
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
    private TableColumn<InvoiceItem, Integer> marginC;
    @FXML
    private TableColumn<InvoiceItem, Date> expireDateC;
    
    InvoiceItemModel iim = new InvoiceItemModel();
    
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
        
        productID.getItems().addAll("A", "B", "C", "D", "E");
        distributerCode.getItems().addAll("A", "B", "C", "D", "E");
    }

    @FXML
    public void addNewDistributer() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Distributer.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Distributer");
            stage.setScene(new Scene(root, 915, 580));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    public void addNewProduct() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/resource/Product.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Product");
            stage.setScene(new Scene(root, 915, 580));
            stage.show();
        } catch (IOException e) {
        }
    }
    
    @FXML
    public void add() {
        InvoiceItem ii = new InvoiceItem();
        ii.setProductID(productID.getValue());
        ii.setPackSize(Integer.parseInt(packSize.getText()));
        ii.setQuantity(Integer.parseInt(quantity.getText()));
        ii.setFree(Integer.parseInt(free.getText()));
        ii.setPrice(Double.parseDouble(price.getText()));
        ii.setMargin(Integer.parseInt(margin.getText()));
        ii.setExpireDate(java.sql.Date.valueOf(expireDate.getValue()));        
        ii.setDiscount(Double.parseDouble(discount.getText()));
        invoiceItemTable.getItems().add(ii);     
    }
    
    @FXML
    public void save() {
        Invoice i = new Invoice();
        i.setInvoiceID(invoiceID.getText());
        i.setDistibutorCode(distributerCode.getValue());
        i.setInvoiceDate(java.sql.Date.valueOf(date.getValue()));
        i.setInvoiceNote(invoiceNote.getText());
        ObservableList<InvoiceItem> items = invoiceItemTable.getItems();
        int j = 1;
        for (int k = 0; k < items.size(); k++) {
            items.get(k).setInvoiceID(invoiceID.getText());
            items.get(k).setItemID("Item-" + j);
            j++;
        }
        iim.addInvoice(i, items);
        
        for (int l = 0; l < invoiceItemTable.getItems().size(); l++) {
            invoiceItemTable.getItems().clear();
        }
    }
    
    @FXML
    public void edit() {}
    
    @FXML
    public void delete() {}
    
    @FXML
    public void clear() {}
    
    @FXML
    public void cancel() {}
}
