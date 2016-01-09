package Controller;

import Model.Service.BillAndInvoiceModel;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

public class BillAndInvoiceController implements Initializable{

    @FXML
    private DatePicker billDate1;
    @FXML
    private DatePicker billDate2;
    @FXML
    private DatePicker invDate1;
    @FXML
    private DatePicker invDate2;
    
    BillAndInvoiceModel bai =  new BillAndInvoiceModel();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public Date getSqlDate(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        return date;
    }
    
    public void billSales(){
        if(billDate1.getValue() != null && billDate2.getValue() == null){
            bai.givenDayBillReport(getSqlDate(billDate1.getValue()));
        }
        else if (billDate1.getValue() != null && billDate2.getValue() != null){
            bai.daysRangeBillReport(getSqlDate(billDate1.getValue()), getSqlDate(billDate2.getValue()));
        }
    }
    
    public void invoiceSales(){
        if(invDate1.getValue() != null && invDate2.getValue() == null){
            bai.givenDayInvoiceReport(getSqlDate(invDate1.getValue()));
        }
        else if (invDate1.getValue() != null && invDate2.getValue() != null){
            bai.daysRangeInvoiceReport(getSqlDate(invDate1.getValue()), getSqlDate(invDate2.getValue()));
        }
    }

    
}
