package Controller;

import Model.Distributor;
import Model.DistributorModel;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button addBtn;    
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
    
    int index;
    
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
        Collections.reverse(distributors);
        return distributors;
    }
    
    @FXML
    public void search() {
    }
    
    @FXML
    public void addNew() {
        Distributor d = new Distributor();
        d.setCode(code.getText());
        d.setName(name.getText());
        d.setAddress(address.getText());
        d.setPhoneNo(phoneNo.getText());
        distributorTable.getItems().add(d);
        if(dm.isExisting(d.getCode())>0){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Distributer code allready Exist");
        }else{
            dm.add(d);
        }
        
        distributorTable.setItems(getDistributor());
        clear();
    }
    
    @FXML
    public void add() {
        
        if(code.isDisable())
            update();
        else
            addNew();
    }
    
    @FXML
    public void edit() {
        
        index = distributorTable.getSelectionModel().getSelectedIndex();
        if(index>=0){
        Distributor d = distributorTable.getItems().get(index);
        code.setText(d.getCode());
        code.setDisable(true);
        name.setText(d.getName());
        address.setText(d.getAddress());
        phoneNo.setText(d.getPhoneNo());
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Please select a distributer to edit");
        }
        addBtn.setText("Update");
        
    }
    
    @FXML
    public void update() {
        Distributor d = new Distributor();
        d.setCode(code.getText());
        code.setDisable(false);
        d.setName(name.getText());
        d.setAddress(address.getText());
        d.setPhoneNo(phoneNo.getText());
        dm.update(d);
        distributorTable.setItems(getDistributor());
        clear();
    }
    
    @FXML
    public void delete() {
        index = distributorTable.getSelectionModel().getSelectedIndex();
        if(index>=0){
        dm.remove(distributorTable.getItems().get(index).getCode());
        distributorTable.getItems().remove(index);
        distributorTable.setItems(getDistributor());
        clear();
    }else{
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Please select a distributer to Delete");
    }}
    
    @FXML
    public void clear() {
        code.clear();
        name.clear();
        address.clear();
        phoneNo.clear();
        code.setDisable(false);
        addBtn.setText("Add");
    }
    
    @FXML
    public void cancel() {
    
    }
    
}