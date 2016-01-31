package Controller;

import Model.DTO.Bill;
import Model.DTO.BillItem;
import Model.Service.BillItemModel;
import Model.Service.ProductModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.TextFields;

public class BillController implements Initializable {

    @FXML
    private TextField billID;
    @FXML
    private TextField billAmount;
    @FXML
    private TextField billNote;
    @FXML
    private DatePicker billDate;
    @FXML
    private TextField discount;
    @FXML
    private ComboBox< String> productID;
    @FXML
    private TextField unitPrice;
    @FXML
    private TextField productName;
    @FXML
    private TextField quantity;
    @FXML
    private TextField total;
    @FXML
    private TableView< BillItem> itemsTable;
    @FXML
    private TableColumn< BillItem, String> productIDC;
    @FXML
    private TableColumn< BillItem, Double> unitPriceC;
    @FXML
    private TableColumn< BillItem, String> productNameC;
    @FXML
    private TableColumn< BillItem, Integer> quantityC;
    @FXML
    private TableColumn< BillItem, Double> totalC;
    
    @FXML
    private Label dateLabel;
    @FXML
    private Label discountLabel;
    @FXML
    private Label productIDLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView icon;
    @FXML
    private Button addBtn;

    Image imageBill = new Image(getClass().getResourceAsStream("/resource/images/billTab.png"));
    Image imageError = new Image(getClass().getResourceAsStream("/resource/images/error.png"));
    Image imageSuccess = new Image(getClass().getResourceAsStream("/resource/images/success.png"));
    Image imageWarnning = new Image(getClass().getResourceAsStream("/resource/images/warnning.png"));
    
    Alert alert;

    private BillItemModel bm = new BillItemModel();
    private ProductModel pm = new ProductModel();
    private Bill b = new Bill();
    private int index;
    private double totalAmount;
    private boolean bool = true;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billID.setText(String.valueOf(getBillId()));
        billID.setDisable(true);
        billAmount.setDisable(true);
        unitPrice.setDisable(true);
        productName.setDisable(true);
        total.setDisable(true);
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        unitPriceC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productNameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        totalC.setCellValueFactory(new PropertyValueFactory<>("total"));
        productID.setItems(getProductID());
        billDate.setValue(LocalDate.now());
        TextFields.bindAutoCompletion(productName, pm.getProductNames());
        quantity.textProperty().addListener(new ChangeListener< String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    itemTotal();
                } else {
                    total.clear();
                }
            }
        });
    }
    
    public int getBillId(){
        return bm.getBillId();
    }
    
    public void itemTotal() {
        if (isDouble(unitPrice.getText()) && isInteger(quantity.getText())) {
            Double u = Double.parseDouble(unitPrice.getText());
            int q = Integer.parseInt(quantity.getText());
            Double tot = u * q;
            total.setText(Double.toString(tot));
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

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public ObservableList< String> getProductID() {
        ObservableList< String> products = bm.getProductsID();
        if(products.isEmpty())
            productID.setPromptText("No products");
        return products;
    }

    public void total() {
        totalAmount = 0;
        for (BillItem bi : itemsTable.getItems()) {
            totalAmount += bi.getTotal();
        }
        billAmount.setText(Double.toString(totalAmount));
    }
        
    public void autoFillById() {
        BillItem bi = bm.getBillItem(productID.getSelectionModel().getSelectedItem());
            if (bi != null) {
            unitPrice.setText(Double.toString(bi.getUnitPrice()));
            if (!productName.getText().equalsIgnoreCase(bi.getProductName())) {
                productName.setText(bi.getProductName());
                bool = false;
            }
        }
    }

    public void autoFillByName() {
        String id = bm.getBillItemByName(productName.getText());
        if (id != null) {
            productID.getSelectionModel().select(id);
        } else {
            unitPrice.clear();
            productID.getSelectionModel().clearSelection();
        }
    }

    public boolean isValidBill() {
        if (billDate.getValue() == null) {
            dateLabel.setText("Required!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidBillItem() {
        if (productID.getSelectionModel().isEmpty()) {
            productIDLabel.setText("Required!");
            return false;
        } else if (quantity.getText().isEmpty()) {
            quantityLabel.setText("Required!");
            return false;
        } else {
            return true;
        }
    }
    
    @FXML
    public void add() {
        if (productID.isDisable()) {
            update();
        } else {
            addNew();
        }
    }
    
    public void addNew() {
        if (isValidBillItem()) {
            if (isInteger(quantity.getText())) {
                BillItem bi = new BillItem();
                bi.setProductID(productID.getValue());
                bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
                bi.setQuantity(Integer.parseInt(quantity.getText()));
                bi.setTotal(Double.parseDouble(total.getText()));
                itemsTable.getItems().add(bi);
                total();

                icon.setImage(imageSuccess);
                clearBillItemFields();
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("New Bill item added");
            } else {
                icon.setImage(imageError);
                quantityLabel.setText("It's not a number");
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Quantity should be a numeric value ");
            }
        } else {
            icon.setImage(imageError);
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }
    }
    
    @FXML
    public void save() {
        if(itemsTable.getItems().size() == 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add bill items before save the bill");
            alert.showAndWait();
        }
        else{
            if (isValidBill()) {
                b.setBillNo(getBillId());
                b.setBillNote(billNote.getText());
                b.setBillDate(billDate.getValue());
                if(discount.getText().equals("")){
                    b.setBillAmount(Double.parseDouble(billAmount.getText()));
                }
                else{
                    if(isDouble(discount.getText())){
                        double dV = Double.parseDouble(discount.getText());
                        b.setBillAmount(Double.parseDouble(billAmount.getText())*(100-dV)/100);
                    }
                    else{
                        messageLabel.setTextFill(Color.RED);
                        messageLabel.setText(" Discound should be decimal value ");
                        return;
                    }
                }
                ObservableList< BillItem> items = itemsTable.getItems();
                bm.addBill(b, items);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Bill added with "+ itemsTable.getItems().size() + " items" );
                alert.showAndWait();
                clearAll();
            } else {
                icon.setImage(imageError);
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Fill all fields ");
            }
        }
    }

    @FXML
    public void edit() {
        index = itemsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            BillItem bi = itemsTable.getItems().get(index);
            productID.setValue(bi.getProductID());
            unitPrice.setText(String.valueOf(bi.getUnitPrice()));
            quantity.setText(String.valueOf(bi.getQuantity()));
            total.setText(String.valueOf(bi.getTotal()));

            productID.setDisable(true);
            addBtn.setText("Update");
        } else {
             icon.setImage(imageWarnning);
             messageLabel.setTextFill(Color.ORANGE);
             messageLabel.setText("Please select a bill item to edit");

        }
    }

    @FXML
    public void update() {
        if (isValidBillItem()) {
            if (isInteger(quantity.getText())) {
                BillItem bi = new BillItem();
                bi.setProductID(productID.getValue());
                bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
                bi.setQuantity(Integer.parseInt(quantity.getText()));
                bi.setTotal(Double.parseDouble(total.getText()));
                index = itemsTable.getSelectionModel().getSelectedIndex();
                itemsTable.getItems().set(index, bi);
                total();
                clearBillItemFields();
                productID.setDisable(false);
                addBtn.setText("Add");
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText(" Bill item updated ");
            } else {
                icon.setImage(imageError);
                quantityLabel.setText("It's not a number");
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Quantity should be a numeric value ");
            }
        } else {
            icon.setImage(imageError);

            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }

    }

    @FXML
    public void delete() {
        index = itemsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
    alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conformation");
            alert.setHeaderText("Are you sure to delete selected invoice item?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                itemsTable.getItems().remove(index);
                total();
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Bill Item is deleted");
            } else if (result.get() == ButtonType.CANCEL){
                alert.close();
            }
        } else {
            icon.setImage(imageWarnning);
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText(" Please select a BillItem to Delete ");
        }
    }
    
    public void clearBillItemFields(){
        productID.setValue(null);
        unitPrice.clear();
        productName.clear();
        quantity.clear();
        total.clear();
    }
    
    public void clearBillFields(){
         billNote.clear();
         discount.clear();
         billAmount.clear();
    }


        /*codeSearch.clear();
         nameSearch.clear();
         addressSearch.clear();
         phoneNoSearch.clear(); */
        

    @FXML
    public void clear() {
        clearBillItemFields();
        clearBillFields();
        addBtn.setText("Add");
    }

    @FXML
    public void cancel() {
        clearBillItemFields();
        clearBillFields();
        itemsTable.getItems().clear();
    }

    @FXML
    public void clearAll() {
        cancel();
    }

    @FXML
    public void dateonPress() {
        dateLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void discountOnPress() {
        discountLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void ProductIDOnPress() {
        productIDLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void quantityOnPress() {
        quantityLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void onPressAnything() {
        messageLabel.setText("");
        icon.setImage(imageBill);

    }

}
