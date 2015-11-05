package Controller;

import Model.Bill;
import Model.BillItem;
import Model.BillItemModel;
import java.net.URL;
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
    private DatePicker billDate;
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

    int index;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        unitPriceC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalC.setCellValueFactory(new PropertyValueFactory<>("total"));
        productID.setItems(getProductID());
    }

    public ObservableList<String> getProductID() {
        ObservableList<String> products = bm.getProductsID();
        return products;
    }

    @FXML
    public void add() {
        BillItem bi = new BillItem();
        bi.setProductID(productID.getValue());
        bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
        bi.setQuantity(Integer.parseInt(quantity.getText()));
        bi.setTotal(Double.parseDouble(total.getText()));
        itemsTable.getItems().add(bi);
        clear();
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
        clear();
        for (int i = 0; i < itemsTable.getItems().size(); i++) {
            itemsTable.getItems().clear();
        }
    }

    @FXML
    public void edit() {
        index = itemsTable.getSelectionModel().getSelectedIndex();
        BillItem bi = itemsTable.getItems().get(index);
        productID.setValue(bi.getProductID());
        unitPrice.setText(String.valueOf(bi.getUnitPrice()));
        quantity.setText(String.valueOf(bi.getQuantity()));
        total.setText(String.valueOf(bi.getTotal()));
    }

    @FXML
    public void update() {
        BillItem bi = new BillItem();
        bi.setProductID(productID.getValue());
        bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
        bi.setQuantity(Integer.parseInt(quantity.getText()));
        bi.setTotal(Double.parseDouble(total.getText()));
        index = itemsTable.getSelectionModel().getSelectedIndex();
        itemsTable.getItems().set(index, bi);
        clear();
    }

    @FXML
    public void delete() {
        index = itemsTable.getSelectionModel().getSelectedIndex();
        itemsTable.getItems().remove(index);
        clear();
    }

    @FXML
    public void clear() {
        productID.setValue(null);
        unitPrice.clear();
        quantity.clear();
        total.clear();
        billAmount.clear();
        discount.clear();
        billNote.clear();
    }

    @FXML
    public void cancel() {

    }

}
