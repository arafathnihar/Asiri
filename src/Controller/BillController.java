package Controller;

import Model.Bill;
import Model.BillItem;
import Model.BillItemModel;
import Model.ProductModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
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
    private TableColumn< BillItem, Integer> quantityC;
    @FXML
    private TableColumn< BillItem, Double> totalC;
    @FXML
    private Label BillIDLbl;
    @FXML
    private Label LabeldateLbl;
    @FXML
    private Label discountLbl;
    @FXML
    private Label totalLbl;
    @FXML
    private Label productIDLbl;
    @FXML
    private Label unitPriceLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label quantityLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private Label messageLabel;
    
    //Image imageDistri = new Image(getClass().getResourceAsStream("/resource/images/distributer.png"));
    Image imageError = new Image(getClass().getResourceAsStream("/resource/images/error.png"));
    Image imageSuccess = new Image(getClass().getResourceAsStream("/resource/images/success.png"));
    Image imageWarnning = new Image(getClass().getResourceAsStream("/resource/images/warnning.png"));

    private BillItemModel bm = new BillItemModel();
    private ProductModel pm = new ProductModel();
    private Bill b = new Bill();
    private int index;
    private double totalAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productIDC.setCellValueFactory(new PropertyValueFactory<>("productID"));
        unitPriceC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalC.setCellValueFactory(new PropertyValueFactory<>("total"));
        productID.setItems(getProductID());
        billDate.setValue(LocalDate.now());
        TextFields.bindAutoCompletion(productName, pm.getProductNames());
        productName.textProperty().addListener(new ChangeListener< String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    autoFillByName();
                }
            }
        });
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

    public void itemTotal() {
        if (isNumeric(unitPrice.getText()) && isNumeric(quantity.getText())) {
            Double unit = Double.parseDouble(unitPrice.getText());
            Double qu = Double.parseDouble(quantity.getText());
            Double tot = unit * qu;
            total.setText(Double.toString(tot));
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
        return true;
    }
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public ObservableList< String> getProductID() {
        ObservableList< String> products = bm.getProductsID();
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
            }
        }
    }

    public void autoFillByName() {
        BillItem bi = bm.getBillItemByName(productName.getText());
        if (bi != null) {
            unitPrice.setText(Double.toString(bi.getUnitPrice()));
            productID.getSelectionModel().select(bi.getProductID());
        } else {
            unitPrice.clear();
            productID.getSelectionModel().clearSelection();
        }
    }
    
    public boolean isValidBill() {
        if (billDate.getValue()==null) {
            LabeldateLbl.setText("Required!");
            return false;
        } else if (billID.getText().isEmpty()) {
            BillIDLbl.setText("Required!");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean isValidBillItem() {
        if (productID.getSelectionModel().isEmpty()) {
            productIDLbl.setText("Required!");
            return false;
        } else if (quantity.getText().isEmpty()) {
            quantityLbl.setText("Required!");
            return false;
        } else {
            return true;
        }
    }

    public void add() {
                    if (isValidBillItem()){
                            if (isInteger(quantity.getText())) {

                                        BillItem bi = new BillItem();
                                        bi.setProductID(productID.getValue());
                                        bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
                                        bi.setQuantity(Integer.parseInt(quantity.getText()));
                                        bi.setTotal(Double.parseDouble(total.getText()));
                                        itemsTable.getItems().add(bi);
                                        total();
                                        clear();
                                        //icon.setImage(imageSuccess);
                                        messageLabel.setTextFill(Color.GREEN);
                                        messageLabel.setText("New Bill item added");

                                    } else {
                                                //icon.setImage(imageError);
                                        quantityLbl.setText("It's not a number");
                                        messageLabel.setTextFill(Color.RED);
                                        messageLabel.setText(" Quantity should be a numeric value ");
                                    }
                            } 
	else{
			//icon.setImage(imageError);
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText(" Fill all fields ");
		}
    }

        /*
    @FXML
    public void add() {
        if (isValidBillItem()) {
                BillItem bi = new BillItem();
                bi.setProductID(productID.getValue());
                bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
                bi.setQuantity(Integer.parseInt(quantity.getText()));
                bi.setTotal(Double.parseDouble(total.getText()));
                itemsTable.getItems().add(bi);
                total();
                clear();
                //icon.setImage(imageSuccess);
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("New Bill item added");
        } else {
            //icon.setImage(imageError);
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }
    }*/

    @FXML
    public void save() {
        if (isValidBill()){
            b.setBillNote(billNote.getText());
            b.setBillAmount(Double.parseDouble(billAmount.getText()));
            ObservableList< BillItem> items = itemsTable.getItems();
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
        else{
            //icon.setImage(imageError);
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
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
        } else {
           // icon.setImage(imageWarnning);
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText("Please select a distributer to edit");
        }
    }

    @FXML
    public void update() {
        if (isValidBillItem()){
                if (isInteger(quantity.getText())) {
                        BillItem bi = new BillItem();
                        bi.setProductID(productID.getValue());
                        bi.setUnitPrice(Double.parseDouble(unitPrice.getText()));
                        bi.setQuantity(Integer.parseInt(quantity.getText()));
                        bi.setTotal(Double.parseDouble(total.getText()));
                        index = itemsTable.getSelectionModel().getSelectedIndex();
                        itemsTable.getItems().set(index, bi);
                        total();
                        clear();
                }
                 else {
                                                //icon.setImage(imageError);
                            quantityLbl.setText("It's not a number");
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText(" Quantity should be a numeric value ");
                }
        }
                             
	else{
                //icon.setImage(imageError);
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Fill all fields ");
	}
                        
    }

    @FXML
    public void delete() {
        index = itemsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            itemsTable.getItems().remove(index);
            total();
            clear();
           // icon.setImage(imageSuccess);
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Bill Item is deleted");
            clear();
        } else {
            //icon.setImage(imageWarnning);
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText(" Please select a BillItem to Delete ");
        }
    }

    @FXML
    public void clear() {
        productID.setValue(null);
        unitPrice.clear();
        productName.clear();
        quantity.clear();
        total.clear();
        billNote.clear();
        
        productIDLbl.setText("");
        quantityLbl.setText("");
        LabeldateLbl.setText("");
        
        /*codeSearch.clear();
        nameSearch.clear();
        addressSearch.clear();
        phoneNoSearch.clear(); */
    }

    @FXML
    public void cancel() {

    }
    
    @FXML
    public void clearAll() {
        clear();
        onPressAnything();
    }
    
    @FXML
    public void billIDonPress() {
       BillIDLbl.setText("");
        onPressAnything();
    }
    
    @FXML
    public void dateonPress() {
        LabeldateLbl.setText("");
        onPressAnything();
    }
    
    @FXML
    public void ProductIDonPress() {
        productIDLbl.setText("");
        onPressAnything();
    }
    
    @FXML
    public void quantityonPress() {
        quantityLbl.setText("");
        onPressAnything();
    }
    
    @FXML
    public void onPressAnything() {
        messageLabel.setText("");
        //icon.setImage(imageDistri);
    }

}
