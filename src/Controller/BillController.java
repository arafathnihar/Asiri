package Controller;

import Model.Bill;
import Model.BillItem;
import Model.BillItemModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BillController implements Initializable {

    @FXML
    private TextField billAmount;
    @FXML
    private TextField billNote;
    @FXML
    private final DatePicker billDate = new DatePicker(LocalDate.now());
    @FXML
    private TextField discount;
    
    @FXML
    private ComboBox<String> productID;
    @FXML
    private TextField unitPrice;
    @FXML
    private TextField quantity;
    @FXML
    private TextField total;
   
    @FXML
    private TableView<BillItem> itemsTable;
    
    @FXML
    private TableColumn<BillItem, String> productIDC;
    @FXML
    private TableColumn<BillItem, Double> unitPriceC;
    @FXML
    private TableColumn<BillItem, Integer> quantityC;
    @FXML
    private TableColumn<BillItem, Double> totalC;

    BillItemModel bm = new BillItemModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        unitPriceC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalC.setCellValueFactory(new PropertyValueFactory<>("total"));
        productID.getItems().addAll("A", "B", "C", "D", "E");
    }

    @FXML
    public void add() {
        BillItem bi = new BillItem();
        bi.setProductID(productID.getValue());
        bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
        bi.setQuantity(Integer.parseInt(quantity.getText()));
        bi.setTotal(Double.parseDouble(total.getText()));
        itemsTable.getItems().add(bi);
        unitPrice.clear();
        quantity.clear();
        total.clear();
    }

    @FXML
    public void save() {
        Bill b = new Bill();
        b.setBillDate(java.sql.Date.valueOf(billDate.getValue()));
        b.setBillNote(billNote.getText());
        b.setBillAmount(Double.parseDouble(billAmount.getText()));
        ObservableList<BillItem> items = itemsTable.getItems();
        int j = 1;
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setBillItemNo("Item-" + j);
            j++;
        }
        bm.addBill(b, items);

        for (int i = 0; i < itemsTable.getItems().size(); i++) {
            itemsTable.getItems().clear();
        }
    }

}
