package Controller;

import Model.Distributor;
import Model.DistributorModel;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author testing
 */
public class DistributerController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNo;
    @FXML
    private TextField codeSearch;
    @FXML
    private TextField nameSearch;
    @FXML
    private TextField addressSearch;
    @FXML
    private TextField phoneNoSearch;
    @FXML
    private TableColumn< Distributor,String> codeC;
    @FXML
    private TableColumn< Distributor,String> nameC;
    @FXML
    private TableColumn< Distributor,String> addressC;
    @FXML
    private TableColumn<Distributor,String> phoneNoC;
    @FXML
    private Label codeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView icon;
    @FXML
    private Button addBtn;
    @FXML
    private TableView<Distributor> distributorTable;

    Image imageDistri = new Image(getClass().getResourceAsStream("/resource/images/distributer.png"));
    Image imageError = new Image(getClass().getResourceAsStream("/resource/images/error.png"));
    Image imageSuccess = new Image(getClass().getResourceAsStream("/resource/images/success.png"));
    Image imageWarnning = new Image(getClass().getResourceAsStream("/resource/images/warnning.png"));

    int index;
    DistributorModel dm = new DistributorModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeC.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNoC.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        refreshDistributors();
    }

    /**
     *
     */
    public void refreshDistributors() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList< Distributor> filteredData = new FilteredList<>(getDistributor(), p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        codeSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(distributor -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (distributor.getCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        nameSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(distributor -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (distributor.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        addressSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(distributor -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (distributor.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 2. Set the filter Predicate whenever the filter changes.
        phoneNoSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(distributor -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare id of every item with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (distributor.getPhoneNo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList< Distributor> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(distributorTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        distributorTable.setItems(sortedData);
        // bind the sortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(distributorTable.comparatorProperty());
    }

    /**
     *
     * @return
     */
    public ObservableList< Distributor> getDistributor() {
        ObservableList< Distributor> distributors = dm.getDistributors();
        Collections.reverse(distributors);
        return distributors;
    }

    /**
     *
     * @return
     */
    public boolean isValid() {
        if (code.getText().isEmpty()) {
            codeLabel.setText("Requird !");
            return false;
        } else if (name.getText().isEmpty()) {
            nameLabel.setText("Requird !");
            return false;
        } else if (address.getText().isEmpty()) {
            addressLabel.setText("Requird !");
            return false;
        } else if (phoneNo.getText().isEmpty()) {
            phoneLabel.setText("Requird !");
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     */
    @FXML
    public void add() {
        if (code.isDisable()) {
            update();
        } else {
            addNew();
        }
    }

    /**
     *
     */
    @FXML
    public void addNew() {
        if (isValid()) {
            if (dm.isExisting(code.getText()) <= 0) {
                Distributor d = new Distributor();
                d.setCode(code.getText());
                d.setName(name.getText());
                d.setAddress(address.getText());
                d.setPhoneNo(phoneNo.getText());
                dm.add(d);
                icon.setImage(imageSuccess);
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("New distributer added");
                clear();
            } else {
                icon.setImage(imageError);
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText(" Distributer code allready Exist ");
                codeLabel.setText("Existing code");
            }
        } else {
            icon.setImage(imageError);
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }
    }

    /**
     *
     */
    @FXML
    public void update() {
        if (isValid()) {
            Distributor d = new Distributor();
            d.setCode(code.getText());
            code.setDisable(false);
            d.setName(name.getText());
            d.setAddress(address.getText());
            d.setPhoneNo(phoneNo.getText());
            dm.update(d);
            icon.setImage(imageSuccess);
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Distributer is updated");
            clear();
        } else {
            icon.setImage(imageError);
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText(" Fill all fields ");
        }
    }

    /**
     *
     */
    @FXML
    public void edit() {
        index = distributorTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Distributor d = distributorTable.getItems().get(index);
            code.setText(d.getCode());
            code.setDisable(true);
            name.setText(d.getName());
            address.setText(d.getAddress());
            phoneNo.setText(d.getPhoneNo());
            addBtn.setText("Update");
            icon.setImage(imageDistri);
            messageLabel.setText("");
        } else {
            icon.setImage(imageWarnning);
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText("Please select a distributer to edit");
        }
    }

    /**
     *
     */
    @FXML
    public void delete() {
        index = distributorTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            dm.remove(distributorTable.getItems().get(index).getCode());
            icon.setImage(imageSuccess);
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Distributer is deleted");
            clear();
        } else {
            icon.setImage(imageWarnning);
            messageLabel.setTextFill(Color.ORANGE);
            messageLabel.setText(" Please select a distributer to Delete ");
        }
    }

    /**
     *
     */
    @FXML
    public void clearSearch() {
        if (!codeSearch.isFocused()) {
            codeSearch.clear();
        }
        if (!nameSearch.isFocused()) {
            nameSearch.clear();
        }
        if (!addressSearch.isFocused()) {
            addressSearch.clear();
        }
        if (!phoneNoSearch.isFocused()) {
            phoneNoSearch.clear();
        }
    }

    /**
     *
     */
    @FXML
    public void clear() {
        codeSearch.clear();
        nameSearch.clear();
        addressSearch.clear();
        phoneNoSearch.clear();

        codeLabel.setText("");
        nameLabel.setText("");
        addressLabel.setText("");
        phoneLabel.setText("");

        code.clear();
        name.clear();
        address.clear();
        phoneNo.clear();

        code.setDisable(false);
        addBtn.setText("Add");
        refreshDistributors();
    }

    /**
     *
     */
    @FXML
    public void clearAll() {
        clear();
        onPressAnything();
    }

    /**
     *
     */
    @FXML
    public void codeOnPress() {
        codeLabel.setText("");
        onPressAnything();
    }

    /**
     *
     */
    @FXML
    public void nameOnPress() {
        nameLabel.setText("");
        onPressAnything();
    }

    /**
     *
     */
    @FXML
    public void addressOnPress() {
        addressLabel.setText("");
        onPressAnything();
    }

    /**
     *
     */
    @FXML
    public void phoneOnPress() {
        phoneLabel.setText("");
        onPressAnything();
    }

    /**
     *
     */
    @FXML
    public void onPressAnything() {
        messageLabel.setText("");
        icon.setImage(imageDistri);
    }
    
}
