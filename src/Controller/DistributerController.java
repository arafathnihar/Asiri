package Controller;

import Model.Distributor;
import Model.DistributorModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Distributor> distributorTable;
    
    @FXML
    private TableColumn<Distributor, String> codeC; 
    @FXML
    private TableColumn<Distributor, String> nameC;
    @FXML
    private TableColumn<Distributor, String> addressC;
    @FXML
    private TableColumn<Distributor, String> phoneNoC;
    
    DistributorModel dm = new DistributorModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeC.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNoC.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        distributorTable.setItems(getDistributor());
    }
    
    public ObservableList<Distributor> getDistributor() {
        ObservableList<Distributor> distributors = dm.getDistributors();
        return distributors;
    }
    
    @FXML
    public void search() {}
    
    @FXML
    public void add() {
        Distributor d = new Distributor();
        d.setCode(code.getText());
        d.setName(name.getText());
        d.setAddress(address.getText());
        d.setPhoneNo(phoneNo.getText());
        distributorTable.getItems().add(d);
        dm.add(d);
        distributorTable.setItems(getDistributor());
        clear();
    }
    
    @FXML
    public void edit() {
        int index = distributorTable.getSelectionModel().getSelectedIndex();
        Distributor d = distributorTable.getItems().get(index);
        code.setText(d.getCode());
        name.setText(d.getName());
        address.setText(d.getAddress());
        phoneNo.setText(d.getPhoneNo());
    }
    
    @FXML
    public void update() {
        Distributor d = new Distributor();
        d.setCode(code.getText());
        d.setName(name.getText());
        d.setAddress(address.getText());
        d.setPhoneNo(phoneNo.getText());
        dm.update(d);
        distributorTable.setItems(getDistributor());
        clear();
    }
    
    @FXML
    public void delete() {
        int index = distributorTable.getSelectionModel().getSelectedIndex();
        dm.remove(distributorTable.getItems().get(index).getCode());
        distributorTable.getItems().remove(index);
        distributorTable.setItems(getDistributor());
        clear();
    }
    
    @FXML
    public void clear() {
        code.clear();
        name.clear();
        address.clear();
        phoneNo.clear();
    }
    
    @FXML
    public void cancel() {}
    
}
