package Controller;

import Model.Service.ReportModel;
import Model.DTO.Bill;
import Model.DTO.Invoice;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportController implements Initializable{

    @FXML
    private TableView<Invoice> invoiceTable;
    @FXML
    private TableColumn<Invoice, String> invoiceIDC;
    @FXML
    private TableColumn<Invoice, Date> invoiceDateC;
    @FXML
    private TableColumn<Invoice, String> DistributorC;
    @FXML
    private TableColumn<Invoice, Double> invoiceTotalC;
    
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Invoice, String> billNoC;
    @FXML
    private TableColumn<Invoice, Date> billDateC;
    @FXML
    private TableColumn<Invoice, String> billNoteC;
    @FXML
    private TableColumn<Invoice, Double> billAmountC;
    
    @FXML
    private DatePicker billDate1;
    @FXML
    private DatePicker billDate2;
    
    @FXML
    private DatePicker invDate1;
    @FXML
    private DatePicker invDate2;
    
    ReportModel rm =  new ReportModel();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invoiceIDC.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
        invoiceDateC.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        DistributorC.setCellValueFactory(new PropertyValueFactory<>("distibutorCode"));
        invoiceTotalC.setCellValueFactory(new PropertyValueFactory<>("invoiceTotal"));
        billNoC.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        billDateC.setCellValueFactory(new PropertyValueFactory<>("billDate"));
        billNoteC.setCellValueFactory(new PropertyValueFactory<>("billNote"));
        billAmountC.setCellValueFactory(new PropertyValueFactory<>("billAmount"));
    }
    
    public java.sql.Date getSqlDate(LocalDate localDate) {
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        return date;
    }
    
    @FXML
    public void billSales(){
        if(billDate1.getValue() != null && billDate2.getValue() == null){
            billTable.setItems(rm.givenDayBillReport(getSqlDate(billDate1.getValue())));
        }
        else if (billDate1.getValue() != null && billDate2.getValue() != null){
            billTable.setItems(rm.daysRangeBillReport(getSqlDate(billDate1.getValue()), getSqlDate(billDate2.getValue())));
        }
    }
    
    @FXML
    public void invoiceSales(){
        if(invDate1.getValue() != null && invDate2.getValue() == null){
            invoiceTable.setItems(rm.givenDayInvoiceReport(getSqlDate(invDate1.getValue())));
        }
        else if (invDate1.getValue() != null && invDate2.getValue() != null){
            invoiceTable.setItems(rm.daysRangeInvoiceReport(getSqlDate(invDate1.getValue()), getSqlDate(invDate2.getValue())));
        }
    }
    
}
