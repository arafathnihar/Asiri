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
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


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
    }
public boolean isValid() {

		if (productID.getSelectionModel().isEmpty()) {
			productIdLabel.setText("Required !");
			return false;
		} else if (quantity.getText().isEmpty()) {
			quantityLabel.setText("Required !");
			return false;
		}else if (price.getText().isEmpty()) {
			priceLabel.setText("Required !");
			return false;
		}else {
			return true;
		}

        }
        public boolean isValidinvoice() {

		if (invoiceID.getText().isEmpty()) {
			invoiceIdLabel.setText("Required!");
			return false;
		}else if (distributerCode.getSelectionModel().isEmpty()) {
			distriCodeLabel.setText("Required!");
			return false;
		}else if (date.getValue()== null) {
			dateLabel.setText("Required!");
			return false;
		}else {
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
        public boolean isFloat(String s){
             try {
                    Float.parseFloat(s); 
                } catch (NumberFormatException e) { 
                    return false;
                } 
            return true;
        }
      
    @FXML
	public void add() {

		if (productID.isDisable()) update();
		else addNew();
	}
        
	@FXML
	public void addNew() {
		if (isValid()) {
                    if (isValidinvoice()){
				if (isInteger(quantity.getText())) {
					if (isFloat(price.getText())) {

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
						//icon.setImage(imageSuccess);
						messageLabel.setTextFill(Color.GREEN);
						messageLabel.setText(" New product added ");
					} else {
						//icon.setImage(imageError);
						priceLabel.setText("It's not a valid number");
						messageLabel.setTextFill(Color.RED);
						messageLabel.setText(" Price should be a decimal value ");
					}
				} else {
					//icon.setImage(imageError);
					quantityLabel.setText("It's not a number");
					messageLabel.setTextFill(Color.RED);
					messageLabel.setText(" Quantity should be a numeric value ");
				}
			
		} else {
			//icon.setImage(imageError);
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText(" Invalid Invoice ");
		}
	}else {
			//icon.setImage(imageError);
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText(" Fill all fields ");
		}
        }
    /*public boolean isValid() {
        if (productID.getSelectionModel().isEmpty()) {
            productIdLabel.setText("Requird !");
            return false;
        } else if (packSize.getText().isEmpty()) {
            packSizeLabel.setText("Requird !");
            return false;
        } else if (quantity.getText().isEmpty()) {
            quantityLabel.setText("Requird !");
            return false;
        } else if (price.getText().isEmpty()) {
            priceLabel.setText("Requird !");
            return false;
        } else if (discount.getText().isEmpty()) {
            discountLabel.setText("Requird !");
            return false;
        } else if (free.getText().isEmpty()) {
            freeLabel.setText("Requird !");
            return false;
        } else if (margin.getText().isEmpty()) {
            marginLabel.setText("Requird !");
            return false;
        } else {
            return true;
        }
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

   /* @FXML
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
        ii.setMargin(Double.parseDouble(margin.getText()));
        ii.setExpireDate(expireDate.getValue());
        ii.setDiscount(Double.parseDouble(discount.getText()));
        invoiceItemTable.getItems().add(ii);
        clear();
    }
*/
    @FXML
    
        public void save() {
                if (isValidinvoice()) {
                    Invoice i = new Invoice();
                    i.setInvoiceID(invoiceID.getText());
                    i.setDistibutorCode(distributerCode.getValue());
                    i.setInvoiceDate(date.getValue());
                    i.setInvoiceNote(invoiceNote.getText());
                    ObservableList < InvoiceItem > items = invoiceItemTable.getItems();
                    int j = 1;
                    for (int k = 0; k < items.size(); k++) {
			items.get(k).setInvoiceID(invoiceID.getText());
			items.get(k).setItemID("Item-" + j);
			j++;
                    }
                    i.setItems(items);
                    iim.addInvoice(i);
                    //iim.addInvoice(i, items);
                    clear();
                    for (int l = 0; l < invoiceItemTable.getItems().size(); l++) {
			invoiceItemTable.getItems().clear();
                    }
	}
        }
    /*
    @FXML
    public void save() {
        Invoice i = new Invoice();
        i.setInvoiceID(invoiceID.getText());
        i.setDistibutorCode(distributerCode.getValue());
        i.setInvoiceDate(date.getValue());
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
    }*/

        
    @FXML
	public void edit() {
		index = invoiceItemTable.getSelectionModel().getSelectedIndex();
                if (index >= 0){
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
                else {
			System.out.println("@@@@@@@@@@@@@@@@@");
			System.out.println("Please select an item to edit");
			//icon.setImage(imageWarnning);
			messageLabel.setTextFill(Color.ORANGE);
			messageLabel.setText("Please select an item to edit");
		}
               
	}
   /* @FXML
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
        productID.setDisable(true);
    }
*/
    @FXML
        public void update() {
                if (isValid()) {

				if (isInteger(quantity.getText())) {
					if (isFloat(price.getText())) {

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
						//icon.setImage(imageSuccess);
						messageLabel.setTextFill(Color.GREEN);
						messageLabel.setText(" product item updated ");
					} else {
						//icon.setImage(imageError);
						priceLabel.setText("It's not a valid number");
						messageLabel.setTextFill(Color.RED);
						messageLabel.setText(" Price should be a decimal value ");
					}
				} else {
					//icon.setImage(imageError);
					quantityLabel.setText("It's not a number");
					messageLabel.setTextFill(Color.RED);
					messageLabel.setText(" Quantity should be a numeric value ");
				}
			
		} else {
			//icon.setImage(imageError);
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText(" Fill all fields ");
		}
	}
    
        /*
    @FXML
    public void update() {
        InvoiceItem ii = new InvoiceItem();
        ii.setProductID(productID.getValue());
        ii.setPackSize(Integer.parseInt(packSize.getText()));
        ii.setQuantity(Integer.parseInt(quantity.getText()));
        ii.setFree(Integer.parseInt(free.getText()));
        ii.setPrice(Double.parseDouble(price.getText()));
        ii.setMargin(Double.parseDouble(margin.getText()));
        ii.setExpireDate(expireDate.getValue());
        ii.setDiscount(Double.parseDouble(discount.getText()));
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        invoiceItemTable.getItems().set(index, ii);
        clear();
    }*/
    @FXML
	public void delete() {
		index = invoiceItemTable.getSelectionModel().getSelectedIndex();
                if (index >= 0) {
			invoiceItemTable.getItems().remove(index);
                        clear();
			//icon.setImage(imageSuccess);
			messageLabel.setTextFill(Color.GREEN);
			messageLabel.setText(" The Product item is Deleted ");
		} else {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("Please select an item to delete");
			//icon.setImage(imageWarnning);
			messageLabel.setTextFill(Color.ORANGE);
			messageLabel.setText("Please select an item to delete");
		}
        }
/*
    @FXML
    public void delete() {
        index = invoiceItemTable.getSelectionModel().getSelectedIndex();
        invoiceItemTable.getItems().remove(index);
        clear();
    }
*/
    @FXML
    public void clear() {
        invoiceID.clear();
        distributerCode.setValue(null);
        invoiceNote.clear();
        productID.setValue(null);
        packSize.clear();
        quantity.clear();
        price.clear();
        discount.clear();
        free.clear();
        margin.clear();
        expireDate.setValue(null);
        
        dateLabel.setText("");
        noteLabel.setText("");
        productIdLabel.setText("");
        packSizeLabel.setText("");
        quantityLabel.setText("");
        priceLabel.setText("");
        discountLabel.setText("");
        freeLabel.setText("");
        marginLabel.setText("");
        expireDateLabel.setText("");

        
        productID.setDisable(false);
       // addBtn.setText("Add");
    }

    @FXML
    public void cancel() {
        invoiceID.clear();
        distributerCode.setValue(null);
        date.setValue(null);
        invoiceNote.clear();
        invoiceItemTable.getItems().clear();
    }
    
    @FXML
    public void clearAll() {
        clear();
        onPressAnything();
    }

    @FXML
    public void idOnPress() {
        productIdLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void quantityOnPress() {
        quantityLabel.setText("");
        onPressAnything();
    }

    

    @FXML
    public void noteOnPress() {
        noteLabel.setText("");
        onPressAnything();
    }

    @FXML
    public void packSizeOnPress() {
        packSizeLabel.setText("");
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
    public void dateOnPress() {
        dateLabel.setText("");
        onPressAnything();
    }
    
    public void distributorOnPress() {
        distriCodeLabel.setText("");
        onPressAnything();
    }
    
    public void invoiceIDOnPress() {
        invoiceIdLabel.setText("");
        onPressAnything();
    }
    
    @FXML
    public void onPressAnything() {
        //icon.setImage(imageProduct);
        messageLabel.setText("");
    }

    @FXML
    public void expandDiscription() {
       // discriptionPane.expandedProperty().set(true);
    }


}
